<template>
  <layout>
    <Title title="打卡">
    </Title>
    <div>
      <el-button type="success" round @click="punch">
        <span>点我打卡</span>
      </el-button>
    </div>
  </layout>
</template>

<script>
import {addPunch} from "@/network/employee";
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
export default {
  components: {
    Layout,
    Title,
  },
  data (){
    return {
      userName: this.$root.UN,
      form: {
        id:0,
        name:this.$root.UN,
        punchTime:new Date()
      }
    }
  },
  methods:{
    punch(){
      console.log(this.form)
      addPunch(this.form).then(res => {
        console.log("??", res)
        if (res.code === '00000') {
          alert('打卡成功');
        }
        else {
          alert('打卡失败');
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
