<template>
  <Layout>

    <Title title="经营情况表"></Title>
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
     <el-button @click="query">查询</el-button>
      <el-button icon = "el-icon-download" @click="exportAsExcel">导出Excel</el-button>
    </div>
    <div>
      <el-table id="out-table"
                :data="sheetContent"
                stripe
                style="width: 100%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
                class="mt15">
        <el-table-column
            prop="allowance"
            label="allowance"
            width="200">
        </el-table-column>
        <el-table-column
            prop="cashCoupon"
            label="cashCoupon"
            width="200">
        </el-table-column>
        <el-table-column
            prop="costOfSales"
            label="costOfSales"
            width="200">
        </el-table-column>
        <el-table-column
            prop="costPriceAdjustmentIncome"
            label="costPriceAdjustmentIncome"
            width="200">
        </el-table-column>
        <el-table-column
            prop="destroy"
            label="destroy"
            width="200">
        </el-table-column>
        <el-table-column
            prop="gift"
            label="gift"
            width="200">
        </el-table-column>
        <el-table-column
            prop="laborCost"
            label="laborCost"
            width="200">
        </el-table-column>
        <el-table-column
            prop="overflowIncome"
            label="overflowIncome"
            width="200">
        </el-table-column>
        <el-table-column
            prop="profit"
            label="profit"
            width="200">
        </el-table-column>
        <el-table-column
            prop="purchaseReturnDifference"
            label="purchaseReturnDifference"
            width="200">
        </el-table-column>
        <el-table-column
            prop="salesIncome"
            label="salesIncome"
            width="200">
        </el-table-column>
        <el-table-column
            prop="totalExpenditure"
            label="totalExpenditure"
            width="200">
        </el-table-column>
        <el-table-column
            prop="totalIncome"
            label="totalIncome"
            width="200">
        </el-table-column>
      </el-table>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import { formatDate } from "@/common/utils";
import {getAllCustomer} from "@/network/purchase";
import {findAllProduct} from "@/network/product";
import {showStatement} from "@/network/finance";

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
      sheetContent: [
        {
          allowance: "",
          cashCoupon: "",
          costOfSales: "",
          costPriceAdjustmentIncome: "",
          destroy: "",
          gift: "",
          laborCost: "",
          overflowIncome: "",
          profit: "",
          purchaseReturnDifference: "",
          salesIncome: "",
          totalExpenditure: "",
          totalIncome: "",
        }
      ],
      name: '',
      customer:'',
      operator:'',
      flag: false,
    }
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
      let excelName = '经营情况.xlsx';
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
    query(){
      console.log(this.beginDate)
      console.log(this.endDate)
      showStatement({
        params:{
          begin: this.beginDate,
          end: this.endDate,
        }
      }).then(_res => {
        this.sheetContent[0].allowance = _res.result.allowance;
        this.sheetContent[0].cashCoupon = _res.result.cashCoupon;
        this.sheetContent[0].costOfSales = _res.result.costOfSales;
        this.sheetContent[0].costPriceAdjustmentIncome = _res.result.costPriceAdjustmentIncome;
        this.sheetContent[0].destroy = _res.result.destroy;
        this.sheetContent[0].gift = _res.result.gift;
        this.sheetContent[0].laborCost = _res.result.laborCost;
        this.sheetContent[0].overflowIncome = _res.result.overflowIncome;
        this.sheetContent[0].profit = _res.result.profit;
        this.sheetContent[0].purchaseReturnDifference = _res.result.purchaseReturnDifference;
        this.sheetContent[0].salesIncome = _res.result.salesIncome;
        this.sheetContent[0].totalExpenditure = _res.result.totalExpenditure;
        this.sheetContent[0].totalIncome = _res.result.totalIncome;
        console.log(this.test)
        console.log(this.sheetContent)
      })
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
