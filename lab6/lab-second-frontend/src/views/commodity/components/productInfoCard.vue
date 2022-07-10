<template>
  <section class="product">
    <div class="product__photo">
      <div class="photo-container">
        <div class="photo-main">
          <img src="@/assets/pic/dell.png" alt="dell computer" />
          <!--          Tip：图片是写死的；关注整个系统，不用太多关注UI-->
        </div>
      </div>
    </div>
    <div class="product__info">
      <div class="title">
        <h1>{{ product_info.name }}</h1>
        <span>ID: {{ product_info.id }}</span>
      </div>
      <!--      查询选择商品后显示的页面-->
      <div class="type">
        <h3>类型</h3>
        <div>{{ product_info.type }}</div>
      </div>
      <div class="quantity">
        库存量 <span>{{ product_info.quantity }}</span>
      </div>

      <div class="description">
        <h3>价格详情</h3>
        <ul>
          <li>进价: {{ product_info.purchasePrice }}</li>
          <li>零售价: {{ product_info.retailPrice }}</li>
          <li>最近进价: {{ product_info.recentPp }}</li>
          <li>最近零售价: {{ product_info.recentRp }}</li>
        </ul>
      </div>
      <button class="create-btn" @click="editProduct(product_info.id)">
        编 辑
      </button>
    </div>

    <!--    点击新建。。。单之后-->
    <el-dialog
        title="修改商品信息"
        :visible.sync="editDialogVisible"
        width="30%"
        @close="close()">
      <el-form :model="editForm" :label-width="'100px'" size="mini">
        <el-form-item label="名 称">
          <el-input v-model="editForm.name" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="类 型">
          <el-input v-model="editForm.type" type="textarea" :rows="2" placeholder="请输入商品类型"></el-input>
        </el-form-item>
        <el-form-item label="进价/零售价">
          <el-col :span="11">
            <el-input v-model="editForm.purchasePrice" placeholder="请输入商品进价" type="number"></el-input>
          </el-col>
          <el-col :span="11" :offset="1">
            <el-input v-model="editForm.retailPrice" placeholder="请输入商品零售价" type="number"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleEdit(false)">取 消</el-button>
        <el-button type="primary" @click="handleEdit(true)">确 定</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
import moment from "moment";
import {getAllCommodity, updateCommodity} from "@/network/commodity";
import {findAllProduct} from "@/network/product";

export default {
  name: "productInfoCard",
  props: ["product_info", "card_type"],
  data() {
    return {
      dialogVisible: false,
      commodityList: [],
      inventory_in: {
        purchasePrice: null,
        quantity: null,
        remark: null,
      },
      editDialogVisible: false,
      editForm: {},
    };
  },
  mounted() {
    this.getAll();
    this.fetchData();
  },
  computed: {
    type_info(){ //根据类型(入库或出库)，显示不同的界面内容
      if (this.card_type === "入库") {
        return {
          quantity: "入库数量",
          price: "进价",
          button_name: "新建入库单",
          dialog_name: "新建入库单",
        };
      } else if (this.card_type === "出库") {
        return {
          quantity: "出库数量",
          price: "123",
          button_name: "新建出库单",
          dialog_name: "新建出库单",
        }
      } else if (this.card_type === '赠送') {
        return {
          quantity: "赠送数量",
          price: "",
          button_name: "新建赠送单",
          dialog_name: "新建赠送单",
        }
      } else if (this.card_type === '报溢'){
        return {
          quantity: "添加数量",
          price: "",
          button_name: "新建报溢单",
          dialog_name: "新建报溢单",
        }
      }
      else if (this.card_type === '报损'){
        return {
          quantity: "减少数量",
          price: "",
          button_name: "新建报损单",
          dialog_name: "新建报损单",
        }
      }else if (this.card_type === '报警'){
        return {
          quantity: "当前数量",
          price: "",
          button_name: "新建报警单",
          dialog_name: "新建报警单",
        }
      }
      return "";
    }
  },
  methods: {
    getAll() {
      getAllCommodity({}).then(_res => {
        this.commodityList = _res.result;
        this.commodityList.forEach((item) => {
          if (item.recentPp == null) {
            item.recentPp = '暂无';
          }
          if (item.recentRp == null) {
            item.recentRp = '暂无';
          }
        })
      }).catch(err => {
        this.$message({
          type: 'error',
          message: '获取商品列表失败!' + err
        })
      })
    },
    fetchData() {
      findAllProduct()
          .then(_res => {
            console.log("fetchProduct", _res);
            this.all_products = _res.result;
          })
          .catch(_err => {
            console.log(_err);
          });
    },
    editProduct(id) {
      this.editDialogVisible = true;
      console.log(id)
      this.editForm = this.commodityList.filter(x => x.id === id)[0];
      if (this.editForm.recentPp === '暂无') {
        this.editForm.recentPp = null;
      }
      if (this.editForm.recentRp === '暂无') {
        this.editForm.recentRp = null;
      }
    },
    handleEdit(type) {
      if (type === false) {
        this.editDialogVisible = false;
        this.editForm = {};
      } else if (type === true) {
        updateCommodity(this.editForm).then(_res => {
          if (_res.code === 'B0003') {
            this.$message({
              type: 'error',
              message: _res.msg
            })
          } else {
            this.$message({
              type: 'success',
              message: '修改成功！'
            })
            this.editForm = {};
            this.editDialogVisible = false;
            this.getAll();
          }
        })
      }
      location.reload()
    },
    handleClose(done) { //el-dialog提供的方法，关闭dialog
      this.$confirm("确认关闭？")
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    submit() {
      this.inventory_in["pid"] = this.product_info.id;
      this.inventory_in["productionDate"] = moment().format(); // 使用moment包创建当前时间
      this.$emit("handleSubmit", this.inventory_in); // 向父组件传递数据
      this.dialogVisible = false; //关闭dialog
    }
  }
};
</script>

<style lang="scss" scoped>
/* ----- Variables ----- */
$color-primary: #4c4c4c;
$color-secondary: #a6a6a6;
$color-highlight: #a486c7;

/* ----- Global ----- */
* {
  box-sizing: border-box;
}

h3 {
  //font-size: 0.7em;
  letter-spacing: 1.2px;
  color: $color-secondary;
}

img {
  max-width: 100%;
  filter: drop-shadow(1px 1px 3px $color-secondary);
}

/* ----- Product Section ----- */
.product {
  display: grid;
  grid-template-columns: 0.9fr 1fr;
  margin: 50px auto;
  padding: 2.5em 0;
  width: 800px;
  background-color: white;
  border-radius: 5px;

  /* ----- Photo Section ----- */
  .product__photo {
    position: relative;

    .photo-container {
      position: absolute;
      left: -2.5em;
      display: grid;
      grid-template-rows: 1fr;
      width: 100%;
      height: 100%;
      border-radius: 6px;
      box-shadow: 4px 4px 25px -2px rgba(0, 0, 0, 0.3);

      .photo-main {
        border-radius: 6px;
        background: radial-gradient(white, #9fa8da);

        img {
          position: absolute;
          left: -3.5em;
          top: 4.5em;
          max-width: 110%;
          filter: saturate(150%) contrast(120%) hue-rotate(10deg)
          drop-shadow(1px 20px 10px rgba(0, 0, 0, 0.3));
        }
      }
    }
  }

  /* ----- Informations Section ----- */
  .product__info {
    padding: 0.8em 0;
    margin-left: 2.5em;

    .title {
      h1 {
        margin-bottom: 0.1em;
        color: $color-primary;
        font-size: 1.5em;
        font-weight: 900;
      }

      span {
        font-size: 0.7em;
        color: $color-secondary;
      }
    }

    .type {
      font-size: 14px;
      letter-spacing: 0.01em;
    }

    .quantity {
      margin: 1.5em 0;
      color: $color-highlight;
      font-size: 1.2em;

      span {
        padding-left: 0.15em;
        font-size: 2.9em;
      }
    }

    .description {
      clear: left;
      margin: 2em 0;

      h3 {
        margin-bottom: 1em;
      }

      ul {
        font-size: 0.8em;
        list-style: disc;
        margin-left: 1em;
      }

      li {
        text-indent: -0.6em;
        margin-bottom: 0.5em;
      }
    }

    .create-btn {
      padding: 1.5em 3.1em;
      border: none;
      border-radius: 7px;
      font-size: 0.8em;
      font-weight: 700;
      letter-spacing: 1.5px;
      color: #fff;
      background-color: $color-highlight;
      box-shadow: 2px 2px 25px -7px $color-primary;
      cursor: pointer;
      margin-left: 50px;

      &:active {
        transform: scale(0.97);
      }
    }
  }

  .basic-info {
    position: relative;

    .photo-box {
      position: absolute;
      top: 25px;
      right: 50px;
      img {
        width: 100px;
        height: 80px;
      }
    }

    .input-content {
      .group 			  {
        position:relative;
        margin:45px 0;
      }
      input 				{
        font-size:18px;
        padding:10px 10px 10px 5px;
        display:block;
        width:300px;
        border:none;
        border-bottom:1px solid #757575;
      }
      input:focus 		{ outline:none; }

      textarea {
        font-size:18px;
        padding:10px 10px 10px 5px;
        width: 300px;
        border: none;
        border-bottom:1px solid #757575;
      }
      textarea:focus {
        outline: none;
      }

      /* LABEL ======================================= */
      label 				 {
        color:#999;
        font-size:18px;
        font-weight:normal;
        position:absolute;
        pointer-events:none;
        left:5px;
        top:10px;
        transition:0.2s ease all;
        -moz-transition:0.2s ease all;
        -webkit-transition:0.2s ease all;
      }

      /* active state */
      input:focus ~ label, input:valid ~ label 		{
        top:-20px;
        font-size:14px;
        color:#5264AE;
      }

      textarea:focus ~ label, textarea:valid ~label {
        top: -20px;
        font-size: 14px;
        color: #5264AE;
      }

      /* BOTTOM BARS ================================= */
      .bar 	{ position:relative; display:block; width:300px; }
      .bar:before, .bar:after 	{
        content:'';
        height:2px;
        width:0;
        bottom:1px;
        position:absolute;
        background:#5264AE;
        transition:0.2s ease all;
        -moz-transition:0.2s ease all;
        -webkit-transition:0.2s ease all;
      }
      .bar:before {
        left: 50%;
      }
      .bar:after {
        right: 50%;
      }

      /* active state */
      input:focus ~ .bar:before, input:focus ~ .bar:after {
        width:50%;
      }

      textarea:focus ~ .bar:before, textarea:focus ~ .bar:after {
        width:50%;
      }

      /* HIGHLIGHTER ================================== */
      .highlight {
        position:absolute;
        height:60%;
        width:100px;
        top:25%;
        left:0;
        pointer-events:none;
        opacity:0.5;
      }

      /* active state */
      input:focus ~ .highlight {
        -webkit-animation:inputHighlighter 0.3s ease;
        -moz-animation:inputHighlighter 0.3s ease;
        animation:inputHighlighter 0.3s ease;
      }

      /* ANIMATIONS ================ */
      @-webkit-keyframes inputHighlighter {
        from { background:#5264AE; }
        to 	{ width:0; background:transparent; }
      }
      @-moz-keyframes inputHighlighter {
        from { background:#5264AE; }
        to 	{ width:0; background:transparent; }
      }
      @keyframes inputHighlighter {
        from { background:#5264AE; }
        to 	{ width:0; background:transparent; }
      }
    }
  }
}
</style>
