<template>
  <layout>
    <Title title="促销策略">
    </Title>
    <div v-if="this.role==='GM'">
    <div>
      <strong>
        增加策略
      </strong>
    </div>
    <div>
      请选择促销策略种类：
      <el-select v-model="type" placeholder="选择促销种类" @change="$forceUpdate()">
        <el-option label="用户级别" value="customer"></el-option>
        <el-option label="特价包" value="special"></el-option>
        <el-option label="总价满减" value="total"></el-option>
      </el-select>
      <el-button @click="submit">提交</el-button>
    </div>
<!--    用户级别-->
    <div>
    <div v-if="type==='customer'">
      <div>
        请输入策略id：
        <input v-model="form1.id" ></input>
      </div>
      <div>
        请输入开始时间：
        <input v-model="form1.beginTime"></input>
      </div>
      <div>
        请输入结束时间：
        <input v-model="form1.endTime"></input>
      </div>
      <div>
        请选择客户级别：
        <el-select v-model="form1.level" placeholder="选择用户级别" @change="$forceUpdate()">
          <el-option label="1" value="1"></el-option>
          <el-option label="2" value="2"></el-option>
          <el-option label="3" value="3"></el-option>
          <el-option label="4" value="4"></el-option>
          <el-option label="5" value="5"></el-option>
        </el-select>
      </div>
      <div>
        请输入折扣（0.01-0.99）：
        <input v-model="form1.discount"></input>
      </div>
      <div>
        请输入代金券金额：
        <input v-model="form1.amount"></input>
      </div>
    </div>
<!--    特价包-->
    <div v-if="type==='special'">
      <div>请输入策略id：
        <input v-model="form2.id"></input>
      </div>
      <div>
        请输入开始时间：
        <input v-model="form2.beginTime"></input>
      </div>
      <div>
        请输入结束时间：
        <input v-model="form2.endTime"></input>
      </div>
      <div>
        请输入优惠金额：
        <input v-model="form2.amount"></input>
      </div>
      <div>
        请输入关联商品id：
        <input v-model="comb1"></input>
      </div>
      <div>
      <input v-model="comb2"></input>
      </div>
    </div>
<!--    总价满减-->
    <div v-if="type==='total'">
      <div>请输入策略id：
        <input v-model="form3.id"></input>
      </div>
      <div>
        请输入开始时间：
        <input v-model="form3.beginTime"></input>
      </div>
      <div>
        请输入结束时间：
        <input v-model="form3.endTime"></input>
      </div>
      <div>
        满减价格：
        <input v-model="form3.condition"></input>
      </div>
      <div>
        请输入代金券金额：
        <input v-model="form3.amount"></input>
      </div>

    </div>
    </div>

    </div>
    <div>
      <div>
        <strong>
          查看策略
        </strong>
      </div>
      选择策略类型：
      <el-select v-model="showType" placeholder="选择促销种类" @change="$forceUpdate()">
        <el-option label="用户级别" value="customer"></el-option>
        <el-option label="特价包" value="combine"></el-option>
        <el-option label="总价满减" value="total"></el-option>
      </el-select>
      <el-button @click="show">查看</el-button>
      <el-table v-if="this.showType ==='customer' "
          :data="sList"
          width="800">
        <el-table-column
            prop="id"
            label="策略id"
            width="200">
        </el-table-column>
        <el-table-column
            prop="beginTime"
            label="开始时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="endTime"
            label="结束时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="level"
            label="用户级别"
            width="200">
        </el-table-column>
        <el-table-column
            prop="discount"
            label="折扣"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="代金券金额"
            width="200">
        </el-table-column>
      </el-table>

      <el-table v-if="this.showType ==='total' "
                :data="sList"
                width="800">
        <el-table-column
            prop="id"
            label="策略id"
            width="200">
        </el-table-column>
        <el-table-column
            prop="beginTime"
            label="开始时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="endTime"
            label="结束时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="condition"
            label="满足金额"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="代金券金额"
            width="200">
        </el-table-column>
      </el-table>

      <el-table v-if="this.showType ==='combine' "
                :data="sList"
                width="800">
        <el-table-column
            prop="id"
            label="策略id"
            width="200">
        </el-table-column>
        <el-table-column
            prop="beginTime"
            label="开始时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="endTime"
            label="结束时间"
            width="200">
        </el-table-column>
        <el-table-column
            prop="pidList[0]"
            label="组合商品1"
            width="200">
        </el-table-column>
        <el-table-column
            prop="pidList[1]"
            label="组合商品2"
            width="200">
        </el-table-column>
        <el-table-column
            prop="amount"
            label="代金券金额"
            width="200">
        </el-table-column>
      </el-table>
    </div>

    <div v-if="this.role === 'GM'">
      <div>
        <strong>
          删除策略
        </strong>
      </div>
      选择策略类型：
      <el-select v-model="deleteType" placeholder="选择促销种类" @change="$forceUpdate()">
        <el-option label="用户级别" value="customer"></el-option>
        <el-option label="特价包" value="combine"></el-option>
        <el-option label="总价满减" value="total"></el-option>
      </el-select>
      输入策略id：
      <input v-model="deleteID"></input>
      <el-button @click="deleteS">删除</el-button>
    </div>
  </layout>
</template>

<script>
import {addPunch} from "@/network/employee";
import {addPromotionTotal, addPromotionCustomer,addPromotionSpecial, deletePromotion, showPromotion} from "@/network/promotion";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
export default {
  components: {
    Layout,
    Title,
  },
  data (){
    return {
      role:"",
      type:"",
      showType:"",
      deleteType:"",
      deleteID:0,
      sList:[],
      comb1:"",
      comb2:"",
      form1:{
        id:0,
        beginTime:"",
        endTime:"",
        level:'',
        discount:0.0,
        amount:0,
      },
      form2:{
        id:0,
        beginTime:"",
        endTime:"",
        amount:0,
        pidList:"",
      },
      form3:{
        id:0,
        beginTime:"",
        endTime:"",
        condition:0,
        amount:0,
      },
    }
  },
  mounted() {
    console.log(sessionStorage.getItem("role"))
    this.role = sessionStorage.getItem("role");
  },
  methods:{
    submit(){
      if(this.type==="customer"){
        console.log(this.type, this.form1)
        addPromotionCustomer(this.form1).then(_res => {
          console.log("客户", this.form1)
        })
      }else if(this.type==="special"){
        this.form2.pidList=[this.comb1, this.comb2]
        console.log(this.form2)
        addPromotionSpecial(this.form2).then(_res => {
          console.log(_res)
          console.log("特价包", this.form2)
        })
      }else if(this.type==="total"){
        console.log(this.form3)
        addPromotionTotal(this.form3).then(_res => {
          console.log("总价",this.form3)
        })
      }
    },
    show(){
      console.log(this.showType)
      showPromotion({
        params:{
          promotionType:this.showType
        }
      }).then(_res => {
        console.log(_res);
        this.sList = _res.result;
      })
    },
    deleteS(){
      deletePromotion({
        params:{
          promotionType: this.deleteType,
          promotionId: this.deleteID
        }
      }).then(_res => {
        console.log("删除", _res)
      })
      location.reload()
    }
  }
}
</script>

<style scoped>

</style>
