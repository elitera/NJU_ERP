<template>
  <InventoryOverflowCard
      :card_type="card_type"
      @handleSubmit="handleSubmit"
  ></InventoryOverflowCard>
</template>

<script>
import InventoryOverflowCard from "@/views/inventory/components/InventoryOverflowCard";
import { warehouseInput } from "@/network/warehouse";
import InventoryOverflow from "@/views/inventory/InventoryOverflow";

export default {
  name: "inventoryIn",
  components: {
    InventoryOverflowCard,
  },
  data() {
    return {
      card_type: "报溢",
    };
  },
  mounted() {
  },
  methods: {
    handleSubmit(submitInfo) {
      console.log("inventoryIn submitInfo", submitInfo); //打印 子组件传递过来的值
      //TODO My:vuex获取操作人姓名
      let tempList = [];
      tempList.push(submitInfo);

      warehouseInput({
        list: tempList,
        operator: "Leonezhurui"
      })
          .then(_res => {
            console.log(_res);
            this.$message({
              message: _res.result,
              type: "success"
            });
          })
          .catch(_err => {
            console.log(_err);
            this.$message({
              message: _err.result,
              type: "error"
            });
          });
    }
  }
};
</script>

<style lang="scss" scoped>
</style>
