<template>
  <Layout>
    <Title title="员工信息"></Title>
    <el-button type="primary" size="medium" @click="_addEmployee">新增员工</el-button>
    <div style="margin-top: 10px">
      <el-table :data="employeeList" stripe style="width: 100%" :header-cell-style="{'text-align':'center'}" :cell-style="{'text-align':'center'}">
        <el-table-column prop="id" label="id" width="60">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="80">
        </el-table-column>
        <el-table-column prop="gender" label="性别" width="70">
        </el-table-column>
        <el-table-column prop="birthday" label="生日" width="100">
        </el-table-column>
        <el-table-column prop="phone" label="电话" width="150">
        </el-table-column>
        <el-table-column prop="role" label="员工岗位" width="150">
        </el-table-column>
        <el-table-column prop="basicSalary" label="员工基本工资" width="100">
        </el-table-column>
        <el-table-column prop="postSalary" label="员工岗位工资" width="200">
        </el-table-column>
        <el-table-column prop="salaryGrantingMode" label="薪资发放方式" width="120">
        </el-table-column>
        <el-table-column prop="salaryCalculatingMode" label="薪资计算方式" width="120">
        </el-table-column>
        <el-table-column prop="account" label="员工账户" width="120">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click.native.prevent="_modifyEmployee(scope)" type="text" size="small">
              编辑
            </el-button>
            <el-button @click.native.prevent="_deleteEmployee(scope)" type="text" size="small">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="dialog-box">
        <el-dialog title="员工信息" height="100%" :visible.sync="dialogFormVisible" custom-class="dialogStyle" :close-on-click-modal="false">
          <el-form :model="form">
            <el-form-item label="id" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.id" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-select v-model="form.gender" placeholder="选择员工性别" @change="$forceUpdate()">
                  <el-option label="男" value="男"></el-option>
                  <el-option label="女" value="女"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="生日" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.birthday" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-col :span="18">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="岗位" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-select v-model="form.role" placeholder="选择用户级别" @change="$forceUpdate()">
                  <el-option label="库存管理人员" value="INVENTORY_MANAGER"></el-option>
                  <el-option label="进货销售人员" value="SALE_STAFF"></el-option>
                  <el-option label="财务人员" value="FINANCIAL_STAFF"></el-option>
                  <el-option label="销售经理" value="SALE_MANAGER"></el-option>
                  <el-option label="人力资源人员" value="HR"></el-option>
                  <el-option label="总经理" value="GM"></el-option>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="基本薪资" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.basicSalary" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="岗位工资" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.postSalary" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="薪资发放方式" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.salaryGrantingMode" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="薪资计算方式" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.salaryCalculatingMode" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="账户" :label-width="formLabelWidth">
              <el-col :span="8">
                <el-input v-model="form.account" autocomplete="off"></el-input>
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
import {employeeShow, employeeAdd, employeeUpdate, employeeDelete, addPunch, showPunch} from "@/network/employee";

const dialogState = {
  add: 1,
  update: 2,
};

export default {
  name: 'EmployeeView',
  components: {
    Layout,
    Title
  },
  data() {
    return {
      employeeList: [
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
        gender: '',
        birthday: '',
        phone: '',
        role: '',
        basicSalary: '',
        postSalary: '',
        salaryGrantingMode: '',
        salaryCalculatingMode: '',
        account: '',
      },

      formLabelWidth: '120px',
      state: '',
    }
  },
  async mounted() {
    await employeeShow({ params: { } }).then(_res => {
      console.log(1,_res.result);
      this.employeeList = this.employeeList.concat(_res.result)
    })
    //sort the list
    this.employeeList.sort(function (a,b){
      return a.id < b.id ? -1: 1;
    });
  },
  methods: {
    filterTag(value, row) {
      return row.type === value
    },
    showEmployeeDialog(id) {
      // TODO: 新增客户
      this.dialogFormVisible = true;
      if (this.state === dialogState.add) {
        this.initForm();//add
      }
      else {
        console.log(id);
        this.form = this.employeeList[id - 1];//update
      }
      // alert('TODO: 新增客户')
    },
    async submitForm() {
      console.log(this.form)
      if (this.state === dialogState.add) {
        console.log("!!")
        //console.log(this.employeeList.length + 1);
        //this.form['id'] = this.employeeList.length + 1;
        console.log(this.form)
        employeeAdd(this.form).then(res => {
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
        employeeUpdate(this.form).then(res => {
          console.log("??", res)
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
      for (var i = 0; i < this.employeeList.length; ++i) {
        if (this.employeeList[i]['id'] === id) {
          return true;
        }
      }
      return false;
    },
    cancel() {
      this.dialogFormVisible = false;
    },

    _addEmployee() {
      this.state = dialogState.add;
      this.showEmployeeDialog(-1);
    },
    _modifyEmployee(scope) {
      console.log("?")
      console.log(scope.row.id)
      this.state = dialogState.update;
      this.showEmployeeDialog(scope.row.id);
    },
    _deleteEmployee(scope) {
      console.log("delete");
      console.log(scope.row.id);
      employeeDelete({
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
