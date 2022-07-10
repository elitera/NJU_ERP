<template>
  <Layout>

    <Title title="经营历程表"></Title>
    <!-- (设定一个时间段，查看此时间段内的出/入库数量/金额，销售/进货的数量/金额。库存数量要有合计，这一点统一于普适需求。） -->
    <div>
      <span><strong>请选择一个时间段: </strong></span>
      <el-date-picker
          v-model="date"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="getData()">
      </el-date-picker>
      <div><strong>请选择单据类型: </strong>
        <el-select v-model="type" placeholder="选择单据类型" @change="$forceUpdate()">
          <el-option label="销售类" value=1></el-option>
          <el-option label="进货类" value=2></el-option>
          <el-option label="财务类" value=3></el-option>
          <el-option label="库存类" value=4></el-option>
        </el-select>
      </div>
      <div>
        <span><strong>请选择客户: </strong></span>
        <el-select v-model="customer" placeholder="选择客户" @change="$forceUpdate()">
          <el-option label="任意" value="null"></el-option>
          <el-option
              v-for="item in customerList"
              :key="item.id"
              :label="item.name"
              :value="item.name"></el-option>
        </el-select>
      </div>
      <span><strong>请输入业务员: </strong></span>
      <input v-model="operator"></input>
      <div>
        <el-button @click="confirm">
          确认
        </el-button>
        <el-button icon = "el-icon-download" @click="exportAsExcel">导出Excel</el-button>
      </div>

<div v-if="flag">
  <p v-for="(item,i) in saleSheetContent">
    订单{{i+1}}： {{item}}
  </p>
</div>

    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { formatDate } from "@/common/utils";
import { getWarehouseIODetailByTime,
  getWarehouseIPQByTime,
  getWarehouseOPQByTime } from "@/network/warehouse";
import {getAllCustomer} from "@/network/purchase";
import {findAllProduct} from "@/network/product";
import {showHistory} from "@/network/finance";

import Excel from "@/views/inventory/excel";
export default {
  components: {
    Excel,
    Layout,
    Title
  },
  data() {
    return {
      date: '',
      inputQuantity: 0,
      outputQuantity: 0,
      saleSheetContent: [],
      purchaseSheetContent:[],
      financeSheetContent:[],
      inventorySheetContent:[],
      type: "",
      customer:'',
      operator:'null',
      customerList:[],
      flag: false,
      form:{
        beginDateStr:'',
        endDateStr:'',
        types:[],
        customer:'',
        operator:'',
      },
    }
  },
  async mounted() {
    await getAllCustomer({ params: { type: 'SUPPLIER' } }).then(_res => {
      console.log(1,_res);
      this.customerList = this.customerList.concat(_res.result)
    })
    await getAllCustomer({ params: { type: 'SELLER' } }).then(_res => {
      console.log(2,_res);
      this.customerList = this.customerList.concat(_res.result)
    })
    await findAllProduct({params: {}}).then(_res=>{
      console.log(3,_res);
      this.productList = _res.result;
    })
  },
  computed: {
    beginDate: function(){
      return this.date === '' ? '' : formatDate(this.date[0])
    },
    endDate: function() {
      return this.date === '' ? '' : formatDate(this.date[1])
    }
  },
  methods: {
    getData() {

    },
    exportAsExcel() {
      //TODO 导出Excel
      console.log("导")
      // getWarehouseExcel().then(_res =>{
      //   console.log(_res);
      // })
      let excelName = '经营历程.xlsx';
      let formName = "out-table";
      if(this.type==1){
        formName = "out-table1";
      }else if(this.type==2){
        formName = "out-table2";
      }else if(this.type==3){
        formName = "out-table3";
      }else if(this.type==4){
        formName = "out-table4";
      }
      console.log(formName)
      var xlsxParam = { raw: true };//转换成excel时，使用原始的格式
      // 克隆节点
      let tables = document.getElementById(formName).cloneNode(true);
      // 判断是否为固定列，解决（为固定列时，会重复生成表格）
      if (tables.querySelector('.el-table__fixed') !== null) {
        tables.removeChild(tables.querySelector('.el-table__fixed'))
      }
      let table_book = this.$XLSX.utils.table_to_book(tables,xlsxParam);
      var table_write = this.$XLSX.write(table_book, {
        bookType: "xlsx",
        bookSST: true,
        type: "array"
      });
      try {
        this.$FileSaver.saveAs(
            new Blob([table_write], { type: "application/octet-stream" }),
            excelName
        );
      } catch (e) {
        if (typeof console !== "undefined") console.log(e, table_write);
      }
      return table_write;
    },
    confirm(){
      console.log("????",this.form)
      this.form.beginDateStr = this.beginDate;
      this.form.endDateStr = this.endDate;
      this.form.customer = this.customer;
      this.form.operator = this.operator;
      this.form.types = [];
      if(this.type==1){
        this.form.types[0] = "XSD";
        this.form.types[1] = "XSTHD";
      }else if(this.type==2){
        this.form.types[0] = "JHD";
        this.form.types[1] = "JHTHD";
      }else if(this.type==3){
        this.form.types[0] = "FKD";
        this.form.types[1] = "SKD";
        this.form.types[2] = "XJFYD";
        this.form.types[3] = "GZD";
      }else if(this.type==4){
        this.form.types[0] = "BYD";
        this.form.types[1] = "BSD";
        this.form.types[1] = "ZSD";
      }
      showHistory(this.form).then(_res=>{
        console.log(_res)
        this.saleSheetContent = _res.result;
      })
      //getWarehouseIODetailByTime(config).then(_res => {
      //   this.sheetContent = _res.result
      //   if (this.sheetContent.length === 0) {
      //     this.$message.error('该时间段内无出入库记录!')
      //   } else {
      //     this.$message.success('查询成功!')
      //   }
      // })
      this.flag=true;
    },
    filterTag(value, row) {
      return row.type === value;
    },
    transform(type) {
      return type === 'warehouse_input' ? '入库' : '出库'
    },
    formatDate
  }
};
</script>

<style scoped lang="scss">
.mt15 {
  margin-top: 15px;
}
</style>
