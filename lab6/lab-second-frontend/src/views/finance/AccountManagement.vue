<template>
  <Layout>
    <Title title="账户管理"></Title>

    <el-col :span="8">
      <el-input v-model="accountinfoInput" placeholder="查询账户"></el-input>
    </el-col>
    <el-button type="primary" :loading="searchAccount.isLoading" style="margin-left:6px" @click="search">{{searchAccount.text}}</el-button>

    <el-row>
      <el-button type="primary" @click="addAccount" style="margin-top:10px">添加账户</el-button>
    </el-row>
    <el-descriptions :title="'账户'+(index+1)" v-for="(item,index) in accountList" :key="index" class="accountInfo">
      <el-descriptions-item label="账户名称">{{item.bankAccountName}}</el-descriptions-item>
      <el-descriptions-item label="账户余额">{{item.balance}}</el-descriptions-item>
      <el-descriptions-item label="操作">
        <el-button type="text" size="small" @click="edit(index)">
          编辑
        </el-button>
        <el-button type="text" size="small" @click="deleteAccount(index)">
          删除
        </el-button>
      </el-descriptions-item>
    </el-descriptions>

    <el-dialog title="账户信息" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="480px">
      <el-form :model="form">
        <el-form-item label="账户名称" :label-width="formLabelWidth">
          <el-col :span="16">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="账户余额" :label-width="formLabelWidth">
          <el-col :span="12">
            <el-input v-model="form.balance" autocomplete="off" :disabled="dialog.isBalanceDisabled"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>

  </Layout>
</template>

<script>


let DialogState = {
  add: 1,
  edit: 2,
}

import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";

import { queryAccount, accountAdd, accountDelete, accountEdit, accountSearch } from '../../network/finance';

export default {
  components: {
    Layout,
    Title
  },
  created() {
  },
  data() {
    return {
      accountList: [],
      accountinfoInput: '',
      dialogFormVisible: false,
      form: {
        name: '',
        balance: '',
      },
      dialog: {
        state: '',
        isBalanceDisabled: false,
      },
      searchAccount: {
        text: '点击查询',
        isLoading: false,
      },
    }
  },

  mounted() {
    queryAccount().then(res => {
      console.log(res);
      if (res.code === '00000') {
        console.log('success!');
        this.accountList = res.result;
      }
      else {
        console.log('error!');
      }
    })
  },



  methods: {
    addAccount() {
      this.initForm();
      this.dialog.state = DialogState.add;
      this.dialog.isBalanceDisabled = false;
      this.dialogFormVisible = true;
    },
    submitForm() {
      let isSuccess = false;
      if (this.dialog.state == DialogState.add) {
        accountAdd(this.form).then(res => {
          if (res.code == '00000') {
            isSuccess = true;
            alert('添加成功');
          }
          else {
            alert('添加失败');
          }
        })

      }
      else {
        accountEdit(this.form).then(res => {
          if (res.code === '00000') {
            isSuccess = true;
            alert('修改成功');
          }
          else {
            alert('修改失败');
          }
        })
      }
      this.dialogFormVisible = false;
      if(isSuccess) {
        location.reload();
      }
    },
    edit(index) {
      this.form = this.copyForm(this.accountList[index]);
      this.dialogFormVisible = true;
      this.dialog.isBalanceDisabled = true;
    },
    copyForm(_form) {
      let tempForm = {};
      for (let i in _form) {
        tempForm[i] = _form[i];
      }
      return tempForm;
    },
    initForm() {
      for (let i in this.form) {
        this.form[i] = '';
      }
    },
    cancel() {
      this.dialogFormVisible = false;
      this.initForm();
    },
    deleteAccount(index) {
      let name = this.accountList[index].name;
      console.log(name);
      accountDelete(name).then(res => {
        if (res.code === '00000') {
          alert('删除成功');
        }
        else {
          alert('删除失败');
        }
      })
    },
    search() {
      this.searchAccount.text = "查询中";
      this.searchAccount.isLoading = true;
      accountSearch(this.accountinfoInput).then(res => {
        if (res.code === '00000') {
          this.accountList = res.result;
          this.searchAccount.text = "点击查询";
          this.searchAccount.isLoading = false;
        }
        else {
          this.accountList = null;
          alert('error');
        }
      })
    }
  }
};
</script>
<style scoped>
.accountInfo {
  margin-top: 20px;
}
</style>
