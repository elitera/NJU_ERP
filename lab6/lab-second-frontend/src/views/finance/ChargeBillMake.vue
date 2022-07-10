<template>
  <Layout>
    <Title title="制定收款单"></Title>

    <el-form :model="form" ref="form" label-width="100px" class="paymentBill">
      <el-form-item label="单据编号" prop="id">
        <el-col :span="6">
          <el-input v-model="form.id"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="客户" prop="supplier">
        <el-col :span="6">
          <el-input v-model="form.supplier"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="操作员" prop="operator">
        <el-col :span="4">
          <el-input v-model="form.operator" :disabled="true"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="转账列表:" prop="transferList">
        <el-form :model="form" ref="list" label-width="80px" class="paymentBill">
          <el-row></el-row>
          <el-form-item label="银行账户" prop="bankAccount">
            <el-col :span="6">
              <el-input v-model="form.transferList[0].bankAccount.bankAccountName"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="账户余额" prop="bankAccount">
            <el-col :span="6">
              <el-input v-model="form.transferList[0].bankAccount.balance"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="转账金额" prop="amount" class="listInput">
            <el-col :span="6">
              <el-input v-model="form.transferList[0].transferAmount"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="备注" prop="remarks" class="listInput">
            <el-col :span="12">
              <el-input v-model="form.transferList[0].remark"></el-input>
            </el-col>
          </el-form-item>
        </el-form>
      </el-form-item>
      <el-form-item label="总额汇总" prop="TotalAmount">
        <el-col :span="6">
          <el-input v-model="form.TotalAmount"></el-input>
        </el-col>
      </el-form-item>
      <el-button type="primary" style="margin-left:100px" @click="confirm">提交</el-button>
    </el-form>

    <el-dialog title="确定提交" :visible.sync="confirmDialogVisiable" width="30%">
      <span slot="footer">
        <el-button @click="confirmDialogVisiable = false">取 消</el-button>
        <el-button type="primary" @click="summit">确 定</el-button>
      </span>
    </el-dialog>
  </Layout>
</template>

<script>


import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";

import { chargeBillMake } from '../../network/finance';

export default {
  components: {
    Layout,
    Title
  },
  created() {
    this.form.operator = sessionStorage.getItem('name');
  },
  data() {
    return {
      form: {
        id: '',
        supplier: '',
        operator: '',
        seller:123,
        transferList: [{
          bankAccount: {
            bankAccountName:'',
            balance:'',
          },
          transferAmount: '',
          remark: '',
        }],
        TotalAmount: '',
        state: "待审批",
      },
      confirmDialogVisiable: false,

    }
  },



  methods: {
    confirm() {
      this.confirmDialogVisiable = true;
    },
    summit() {
      this.confirmDialogVisiable = false;
      console.log(this.form)
      chargeBillMake(this.form).then(res => {
        if(res.code === '00000') {
          alert('提交成功');
        }
        else {
          alert('提交失败');
        }
      })
      location.reload()
      //this.clearForm();
    },
    clearForm() {
      for (let i in this.form) {
        this.form[i] = '';
      }
    }
  }
};
</script>
<style scoped>
.listInput {
  margin-top: 20px;
}
</style>
