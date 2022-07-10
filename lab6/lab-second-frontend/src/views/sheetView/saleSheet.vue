<template>
  <Layout>

    <Title title="销售明细表"></Title>
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
      <div><strong>请选择商品名: </strong>
      <el-select v-model="name" placeholder="选择商品名" @change="$forceUpdate()">
        <el-option label="任意" value="null"></el-option>
        <el-option
        v-for="item in productList"
        :key="item.id"
        :label="item.name"
        :value="item.name"></el-option>
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
      <div v-if="flag !== false" class="mt15">
        <div class="mt15">
          <span><strong>销售明细表</strong></span>
          <el-table id="out-table"
              :data="sheetContent"
              stripe
              style="width: 100%"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}"
              class="mt15">
            <el-table-column
                prop="createTime"
                label="创建时间"
                width="200">
            </el-table-column>
            <el-table-column
                prop="name"
                label="商品名"
                width="200">
            </el-table-column>
            <el-table-column
                prop="quantity"
                label="数量"
                width="200">
            </el-table-column>
            <el-table-column
                prop="totalPrice"
                label="总价"
                width="150">
            </el-table-column>
            <el-table-column
                prop="type"
                label="类型"
                width="150">
            </el-table-column>
            <el-table-column
                prop="unitPrice"
                label="单价"
                width="150">
            </el-table-column>
          </el-table>
        </div>
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
import {showDetail} from "@/network/finance";

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
      sheetContent: [],
      name: '',
      customer:'',
      operator:'null',
      flag: false,
      customerList:[],
      productList:[],
      form:{
        beginDateStr: '',
        endDateStr: '',
        name:'',
        customer: '',
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
      let formName = "out-table";
      let excelName = '销售明细.xlsx';
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
      this.form.beginDateStr=this.beginDate;
      this.form.endDateStr=this.endDate;
      this.form.name=this.name;
      this.form.customer=this.customer;
      this.form.operator=this.operator;
      console.log(this.form)
      showDetail(this.form).then(_res => {
        console.log(_res)
        this.sheetContent = _res.result;
      })

      // getWarehouseIODetailByTime(config).then(_res => {
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
