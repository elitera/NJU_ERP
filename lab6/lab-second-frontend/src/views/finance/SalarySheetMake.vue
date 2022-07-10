<template>
  <Layout>
    <Title title="制定工资单"></Title>

    <el-form :model="form" ref="form" label-width="100px" class="paymentBill">
      <el-form-item label="员工编号" prop="stuffId">
        <el-col :span="6">
          <el-input v-model="form.stuffId"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-col :span="4">
          <el-input v-model="form.name"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="转账列表:" prop="bankAccount">
        <el-form :model="form" ref="list" label-width="80px" class="paymentBill">
          <el-row></el-row>
          <el-form-item label="银行账户" prop="bankAccountName">
            <el-col :span="6">
              <el-input v-model="form.bankAccount.bankAccountName"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="账户余额" prop="balance">
            <el-col :span="6">
              <el-input v-model="form.bankAccount.balance"></el-input>
            </el-col>
          </el-form-item>
        </el-form>
      </el-form-item>
      <el-form-item label="应发工资" prop="rowWages">
        <el-col :span="6">
          <el-input v-model="form.rowWages"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="个人所得税" prop="individualIncomeTax">
        <el-col :span="6">
          <el-input v-model="form.individualIncomeTax"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="失业保险" prop="unemploymentInsurance">
        <el-col :span="6">
          <el-input v-model="form.unemploymentInsurance"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="住房公积金" prop="housingProvidentFund">
        <el-col :span="6">
          <el-input v-model="form.housingProvidentFund"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="扣除税款" prop="tax">
        <el-col :span="6">
          <el-input v-model="form.tax"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="实发工资" prop="realWages">
        <el-col :span="6">
          <el-input v-model="form.realWages"></el-input>
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

import { salarySheetMake } from '@/network/finance';

export default {
  components: {
    Layout,
    Title
  },
  data() {
    return {
      form: {
        stuffId: '',
        name: '',
        bankAccount: {
            bankAccountName: '',
            balance: '',
        },
        rowWages: '',
        individualIncomeTax: '',
        unemploymentInsurance: '',
        housingProvidentFund: '',
        tax: '',
        realWages: '',
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
      salarySheetMake(this.form).then(res => {
        if (res.code === '00000') {
          alert('提交成功');
        }
        else {
          alert('提交失败');
        }
      })
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
