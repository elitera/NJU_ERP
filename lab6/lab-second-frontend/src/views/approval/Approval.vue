<template>
  <Layout>
    <div class="card">
      <h1>待审批</h1>
      <strong>收款单</strong>
      <div v-if="skdList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in skdList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveSKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denySKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
      <strong>付款单</strong>
      <div v-if="fkdList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in fkdList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveFKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denyFKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
    </div>
    <strong>工资单</strong>
    <div v-if="gzdList.length === 0">
      暂无数据
    </div>
    <el-card v-for="(item, index) in gzdList" shadow="hover">
      <template #header>
        <el-row>
          <el-col :span="18">
            <span><strong>id: </strong>{{item.id}}</span>
            <el-button v-if="authorization() === 1&& item.state === '待审批'" style="margin-left: 10px"
                       type="success" icon="el-icon-check" circle size="mini" @click="approveGZD(item.id)"></el-button>
            <el-button v-if="authorization() === 1&& item.state === '待审批'"
                       type="danger" icon="el-icon-close" circle size="mini" @click="denyGZD(item.id)"></el-button>
            <span style="margin-left: 10px">
            </span>
          </el-col>
        </el-row>
      </template>
    </el-card>

    <div class="card">
      <h1>审批成功</h1>
      <strong>收款单</strong>
      <div v-if="skdSuccessList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in skdSuccessList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1 && item.state === '待审批'" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveSKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1 && item.state === '待审批'"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denySKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
      <strong>付款单</strong>
      <div v-if="fkdSuccessList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in fkdSuccessList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1 && item.state === '待审批'" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveFKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1 && item.state === '待审批'"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denyFKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
    </div>
    <strong>工资单</strong>
    <div v-if="gzdList.length === 0">
      暂无数据
    </div>
    <el-card v-for="(item, index) in gzdSuccessList" shadow="hover">
      <template #header>
        <el-row>
          <el-col :span="18">
            <span><strong>id: </strong>{{item.id}}</span>
            <el-button v-if="authorization() === 1&& item.state === '待审批'" style="margin-left: 10px"
                       type="success" icon="el-icon-check" circle size="mini" @click="approveGZD(item.id)"></el-button>
            <el-button v-if="authorization() === 1&& item.state === '待审批'"
                       type="danger" icon="el-icon-close" circle size="mini" @click="denyGZD(item.id)"></el-button>
            <span style="margin-left: 10px">
            </span>
          </el-col>
        </el-row>
      </template>
    </el-card>

    <div class="card">
      <h1>审批失败</h1>
      <strong>收款单</strong>
      <div v-if="skdFailedList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in skdFailedList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1 && item.state === '待审批'" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveSKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1 && item.state === '待审批'"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denySKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
      <strong>付款单</strong>
      <div v-if="fkdList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in fkdFailedList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1&& item.state === '待审批'" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveFKD(item.id)"></el-button>
              <el-button v-if="authorization() === 1&& item.state === '待审批'"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denyFKD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>
      <strong>工资单</strong>
      <div v-if="gzdList.length === 0">
        暂无数据
      </div>
      <el-card v-for="(item, index) in gzdFailedList" shadow="hover">
        <template #header>
          <el-row>
            <el-col :span="18">
              <span><strong>id: </strong>{{item.id}}</span>
              <el-button v-if="authorization() === 1&& item.state === '待审批'" style="margin-left: 10px"
                         type="success" icon="el-icon-check" circle size="mini" @click="approveGZD(item.id)"></el-button>
              <el-button v-if="authorization() === 1&& item.state === '待审批'"
                         type="danger" icon="el-icon-close" circle size="mini" @click="denyGZD(item.id)"></el-button>
              <span style="margin-left: 10px">
            </span>
            </el-col>
          </el-row>
        </template>
      </el-card>

    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {paymentOrderApproval, receiptApproval, receiptSheetById, payrollById, paymentOrderById, payrollApproval} from "@/network/finance";
import {showHistory} from "@/network/finance";

export default {
  name: 'PurchaseView',
  components: {
    Layout,
    Title,
  },
  data() {
    return {
      Id:"",
      test:"test",
      FKD:{
        beginDateStr:"",
        endDateStr:"",
        customer:"null",
        operator:"null",
        types:["FKD"],
      },
      SKD:{
        beginDateStr:"",
        endDateStr: "",
        customer:"null",
        operator:"null",
        types:["SKD"],
      },
      GZD:{
        beginDateStr:"",
        endDateStr: "",
        customer:"null",
        operator:"null",
        types:["GZD"],
      },
      fkdList:[],
      skdList:[],
      fkdSuccessList:[],
      fkdFailedList:[],
      skdSuccessList:[],
      skdFailedList:[],
      gzdList:[],
      gzdSuccessList:[],
      gzdFailedList:[],
      dialogVisible: false,
    }
  },
  mounted() {
    showHistory(this.SKD).then(_res => {
      console.log("res1",_res)
      console.log(this.fkdList)
      for(var i=0;i<_res.result.length; i++){
        var test = _res.result[i];
        console.log("test",test)
        var flag = "";
        paymentOrderById({
          params:{
            id: test
          }
        }).then(_res => {
          if(_res.result.state === "待审批") {
            this.fkdList = this.fkdList.concat(_res.result)
          }
          else if(_res.result.state === "审批完成") {
            this.fkdSuccessList = this.fkdSuccessList.concat(_res.result)
          }
          else if(_res.result.state === "审批失败") {
            this.fkdFailedList = this.fkdFailedList.concat(_res.result)
          }
        })
      }

    })
    showHistory(this.FKD).then(_res => {
      console.log("res2",_res)
      for(var i=0;i<_res.result.length; i++){
        var test = _res.result[i];
        var flag = "";
        receiptSheetById({
          params:{
            id: test
          }
        }).then(_res => {
          if(_res.result.state === "待审批")
            this.skdList = this.skdList.concat(_res.result)
          else if(_res.result.state === "审批完成")
            this.skdSuccessList = this.skdSuccessList.concat(_res.result)
          else if(_res.result.state === "审批失败")
            this.skdFailedList = this.skdFailedList.concat(_res.result)
        })
      }
    })
    showHistory(this.GZD).then(_res => {
      console.log("res3",_res)
      for(var i=0;i<_res.result.length; i++){
        var test = _res.result[i];
        var flag = "";
        payrollById({
          params:{
            id: test
          }
        }).then(_res => {
          console.log("rnm",_res)
          if(_res.result.state === "待审批") {
            console.log("last")
            this.gzdList = this.gzdList.concat(_res.result)
          }
          else if(_res.result.state === "审批完成")
            this.gzdSuccessList = this.gzdSuccessList.concat(_res.result)
          else if(_res.result.state === "审批失败")
            this.gzdFailedList = this.gzdFailedList.concat(_res.result)
        })
      }
    })
    console.log("fkdlist", this.fkdList)
    console.log("fkdssl", this.fkdSuccessList)
    console.log("fkdfl",this.fkdFailedList)

    console.log("fkdlist111", this.skdList)
    console.log("fkdssl111", this.skdSuccessList)
    console.log("fkdfl111",this.fkdFailedList)

    console.log("gzd1", this.gzdList)
    console.log("gzd2", this.gzdSuccessList)
    console.log("gzd3",this.gzdFailedList)
  },
  methods: {
    sh() {
      console.log("fkdlist", this.fkdList)
      console.log("fkdssl", this.fkdSuccessList)
      console.log("fkdfl",this.fkdFailedList)

      console.log("fkdlist111", this.skdList)
      console.log("fkdssl111", this.skdSuccessList)
      console.log("fkdfl111",this.fkdFailedList)
    },
    authorization() {
      if (sessionStorage.getItem('role') === 'GM') {
        return 1
      } else{
        return 2
      }
    },
    approveFKD(id){
      paymentOrderApproval({
        params:{
          paymentOrderId: id,
          state: "审批完成"
        }
      }).then(_res => {
        console.log("审批完成",_res)
      })
      location.reload()
    },
    denyFKD(id){
      paymentOrderApproval({
        params:{
          paymentOrderId: id,
          state: "审批失败"
        }
      }).then(_res => {
        console.log("审批失败",_res)
      })
      location.reload()
    },
    approveSKD(id){
      console.log("skd",id)
      receiptApproval({
        params:{
          receiptId: id,
          state: "审批完成"
        }
      }).then(_res => {
        console.log("审批完成",_res)
      })
      location.reload()
    },
    denySKD(id){
      receiptApproval({
        params:{
          receiptId: id,
          state: "审批失败"
        }
      }).then(_res => {
        console.log("审批失败",_res)
      })
      location.reload()
    },
    approveGZD(id){
      payrollApproval({
        params:{
          payrollId: id,
          state: "审批完成"
        }
      }).then(_res => {
        console.log("审批完成",_res)
      })
    },
    denyGZD(id){
      payrollApproval({
        params:{
          receiptId: id,
          state: "审批失败"
        }
      }).then(_res => {
        console.log("审批失败",_res)
      })
      location.reload()
    },
  }
}
</script>

<style lang="scss" scoped>
.body {
  margin: 0 auto;
  margin-top: 10px;
  height: 80vh;
  overflow-y: auto;
  width: 100%;
  background: rgba($color: #fff, $alpha: 0.5);
}
</style>
