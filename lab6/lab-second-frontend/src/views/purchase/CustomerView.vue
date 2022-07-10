<template>
  <Layout>
    <Title title="客户管理"></Title>
    <el-button type="primary" size="medium" @click="_addCustomer">新增客户</el-button>
    <div style="margin-top: 10px">
      <el-table :data="customerList" stripe style="width: 100%" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="id" label="id" width="60">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="70">
        </el-table-column>
        <el-table-column prop="type" label="类型" width="100" :filters="[{ text: '供应商', value: '供应商' }, { text: '销售商', value: '销售商' }]" :filter-method="filterTag" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.type === '供应商' ? 'primary' : 'success'" disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="level" label="级别" width="50">
        </el-table-column>
        <el-table-column prop="phone" label="电话" width="150">
        </el-table-column>
        <el-table-column prop="address" label="地址" width="150">
        </el-table-column>
        <el-table-column prop="zipcode" label="邮编" width="100">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="200">
        </el-table-column>
        <el-table-column prop="lineOfCredit" label="应收额度(元)" width="120">
        </el-table-column>
        <el-table-column prop="receivable" label="应收(元)" width="120">
        </el-table-column>
        <el-table-column prop="payable" label="应付(元)" width="120">
        </el-table-column>
        <el-table-column prop="operator" label="操作员" width="120">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click.native.prevent="_modifyCustomer(scope.row.id)" type="text" size="small">
              编辑
            </el-button>
            <el-button @click.native.prevent="_deleteCustomer(scope)" type="text" size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="dialog-box">
        <el-dialog title="用户信息" height="100%" :visible.sync="dialogFormVisible" custom-class="dialogStyle" :close-on-click-modal="false">
          <el-form :model="form">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="类型" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-select v-model="form.type" placeholder="选择用户类型" @change="$forceUpdate()">
                  <el-option label="供应商" value="供应商"></el-option>
                  <el-option label="销售商" value="销售商"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="级别" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-select v-model="form.level" placeholder="选择用户级别" @change="$forceUpdate()">
                  <el-option label="1" value="1"></el-option>
                  <el-option label="2" value="2"></el-option>
                  <el-option label="3" value="3"></el-option>
                  <el-option label="4" value="4"></el-option>
                  <el-option label="5" value="5"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-col :span="18">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="邮编" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.zipcode" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="应收额度（元）" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.lineOfCredit" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="应收（元）" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.receivable" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="应付（元）" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.payable" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="操作员" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.operator" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="cancel">取 消</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {getAllCustomer, addCustomer, modifyCustomer, deleteCustomer} from "@/network/purchase";

var dialogState = {
  add: 1,
  update: 2,
}

export default {
  name: 'CustomerView',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      customerList: [
        // {
        //   id: '1',
        //   name: '',
        //   type: '',
        //   level: '',
        //   address: '',
        //   zipcode: '',
        //   email: '',
        //   lineOfCredit: '',
        //   receivable: '',
        //   payable: '',
        //   operator: '',
        // },
        //         {
        //   id: '2',
        //   name: '',
        //   type: '',
        //   level: '',
        //   address: '',
        //   zipcode: '',
        //   email: '',
        //   lineOfCredit: '',
        //   receivable: '',
        //   payable: '',
        //   operator: '',
        // }
      ],
      dialogFormVisible: false,
      form: {
        id: '',
        name: '',
        type: '',
        level: '',
        address: '',
        zipcode: '',
        email: '',
        lineOfCredit: '',
        receivable: '',
        payable: '',
        operator: '',
      },

      formLabelWidth: '120px',
      state: '',
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
      console.log(3,this.customerList)
    })
    //sort the list
    this.customerList.sort(function (a,b){
      return a.id < b.id ? -1: 1;
    });
  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },
    showCustomerDialog(id) {
      // TODO: 新增客户
      this.dialogFormVisible = true;
      if (this.state === dialogState.add) {
        this.initForm();//add
      }
      else {
        console.log(id);
        this.form = this.customerList[id - 1];//update
      }
      // alert('TODO: 新增客户')
    },
    async submitForm() {
      if (this.state === dialogState.add) {
        console.log(this.customerList.length + 1);
        //this.form['id'] = this.customerList.length + 1;
        console.log(this.form)
        addCustomer(this.form).then(res => {
          console.log(res);
          if (res.code === '00000') {
            alert('添加成功');
          }
          else {
            alert('添加失败');
          }
        })
      }
      else {
        modifyCustomer(this.form).then(res => {
          if (res.code === '00000') {
            alert('修改成功');
          }
          else {
            alert('修改失败');
          }
        })
      }
      this.dialogFormVisible = false;
      location.reload();
    },
    copyForm(form) {
      let copy = {};
      for (let i in form) {
        copy[i] = form[i];
      }
      return copy;
    },
    initForm() {
      for (let i in this.form) {
        this.form[i] = '';
      }
    },
    formExist(id) {
      for (var i = 0; i < this.customerList.length; ++i) {
        if (this.customerList[i]['id'] === id) {
          return true;
        }
      }
      return false;
    },
    cancel() {
      this.dialogFormVisible = false;
    },

    _addCustomer() {
      this.state = dialogState.add;
      this.showCustomerDialog(-1);
    },
    _modifyCustomer(id) {
      this.state = dialogState.update;
      this.showCustomerDialog(id);
    },
    _deleteCustomer(scope) {
      console.log("delete");
      console.log(scope.row.id);
      deleteCustomer({
        params: {
          id: scope.row.id,
        }
      }).then(_res => {
        console.log("delete", _res);
      })
      location.reload();
    }
  }
}
</script>

<style lang="scss" scoped>
.dialog-box {
  //弹出层的高度
  ::v-deep .el-dialog.dialogStyle {
    height: auto;
    max-height: 60vh;
    overflow-y: auto;
  }
  //弹出层里内容的高度
  ::v-deep .el-dialog__body {
    max-height: 90vh !important;
  }
}
</style>
