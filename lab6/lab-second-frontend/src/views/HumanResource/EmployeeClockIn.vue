<template>
  <Layout>
    <Title title="打卡"></Title>
    请输入员工用户名
    <input v-model="name"></input>
    <el-button type="primary" size="medium" @click="_click">查询</el-button>
    <div v-if="flag">
      <el-table
        :data="content"
      width="800">
        <el-table-column
            prop="id"
            label="打卡记录"
            width="400">
        </el-table-column>
        <el-table-column
            prop="name"
            label="员工"
            width="400">
        </el-table-column>
        <el-table-column
            prop="punchTime"
            label="打卡时间"
            width="400">
        </el-table-column>
      </el-table>
    </div>
  </Layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {showPunch}  from "@/network/employee";
export default {
  data() {
    return {
      flag: false,
      name: '',
      content: [],
    }
  },
  components: {
    Layout,
    Title,
    showPunch,

  },
  methods: {
    _click(){
      console.log(this.name)
      console.log(1,this.$root.UN);
      this.flag=true;
      showPunch({
        params: {
          name: this.name
        }
      }).then(_res => {
        console.log(_res.result)
        this.content = _res.result;
      })
    }
  }
};
</script>

<style scoped>
</style>
