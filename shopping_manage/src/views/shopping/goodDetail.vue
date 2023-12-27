<template>
  <div class="detailView">
    <div class="detailBox">
      <el-page-header @back="goBack"  title=" " />
      <div class="detail">
        <div class="left">
        <el-carousel indicator-position="outside" height="400px">
          <el-carousel-item v-if="good.imgShow1">
            <el-image :src="good.imgShow1" />
          </el-carousel-item>
          <el-carousel-item v-if="good.imgShow2">
            <el-image :src="good.imgShow2" />
          </el-carousel-item>
          <el-carousel-item v-if="good.imgShow3">
            <el-image :src="good.imgShow3" />
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="right">
        <h1>{{ good.goodsName }}</h1>
        <h2>
          价格:<span class="price-text">{{ data.price }}</span
          >元
        </h2>
        <div v-for="item in info" :key="item.id" class="attributeItem">
          <span
            @click="select(item)"
            v-bind:class="{
              select_span: item.id === data.goodInfoId,
              default_span: item.id !== data.goodInfoId,
            }"
            >{{ item.attribute }}</span
          >
        </div>
        <div>
          <span>数量：</span
          ><el-input-number
            style="width: 100px"
            v-model="data.amount"
          ></el-input-number>
        </div>
        <div class="buttonGroup">
          <el-button type="success" @click="addCarClick" size="large"
            >添加到购物车</el-button
          >
          <el-button type="primary" @click="buyClick" size="large"
            >购买</el-button
          >
        </div>
      </div>
      </div>
      
    </div>
    <div class="middle">
      <el-card class="comment">
        <template #header>
          <div class="top">
            <h2>评论</h2>
            <span @click="drawer = true">更多</span>
          </div>
        </template>
        <el-card
          v-for="comment in allComments1"
          :key="comment.id"
          class="comment-item"
        >
          <div class="top">
            <strong>{{ comment.nickName }}</strong>
            <strong>发布日期：{{ comment.createTime }}</strong>
            <strong
              :style="{ color: comment.commentType === 0 ? 'green' : 'red' }"
              >{{ comment.commentType === 0 ? "好评" : "差评" }}</strong
            >
          </div>
          <p>
            {{ comment.content }}
          </p>
        </el-card>
      </el-card>
    </div>
  </div>
  <el-drawer v-model="drawer" size="50%" title="所有评论">
    <el-card
      v-for="comment in pagedDrawerComments"
      :key="comment.id"
      class="comment-item"
    >
      <div class="top">
        <strong>{{ comment.nickName }}</strong>
        <strong>发布日期：{{ comment.createTime }}</strong>
        <strong
          :style="{ color: comment.commentType === 0 ? 'green' : 'red' }"
          >{{ comment.commentType === 0 ? "好评" : "差评" }}</strong
        >
      </div>
      <p>
        {{ comment.content }}
      </p>
    </el-card>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="allComments.length"
      @current-change="handleDrawerPageChange"
      :page-size="drawerPageSize"
      :current-page="drawerCurrentPage"
    />
  </el-drawer>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from "vue";
import { ElMessage } from "element-plus";
import { selectGoodsById, selectGoodsInfoById } from "../../request/addGoods";
import { addShoppingCar } from "../../request/order";
import { selectCommentsBygoodId } from "../../request/user";
import router from "@/router";
const good = ref({});
const info = ref([]);
const allComments1 = ref([]);
const allComments = ref([]);
const drawer = ref(false);
const drawerCurrentPage = ref(1); // 抽屉中的当前页码
const drawerPageSize = ref(4); // 抽屉中每页显示的评论数量
const data = reactive({
  userId: null,
  goodId: null,
  goodInfoId: null,
  price: null,
  amount: 1,
});
const order = ref([]);
onMounted(async () => {
  data.userId = sessionStorage.getItem("userId");
  data.goodId = sessionStorage.getItem("goodsId");
  await selectGoodsById(data.goodId).then((res) => {
    good.value = res.data.data;
  });
  await selectGoodsInfoById(data.goodId).then((res) => {
    info.value = res.data.data;
  });
  data.price = info.value[0].price;
  await selectCommentsBygoodId(data.goodId).then((res) => {
    allComments1.value = res.data.data.slice(0, 3); // 截取前3条评论
  });
  await selectCommentsBygoodId(data.goodId).then((res) => {
    allComments.value = res.data.data;
  });
});

const select = (item) => {
  data.price = item.price;
  data.goodInfoId = item.id;
};

const addCarClick = async () => {
  await addShoppingCar(data);
  ElMessage.success("添加成功");
};

const buyClick = async () => {
  if (data.goodInfoId === null) {
    ElMessage.error("请选择商品");
  } else {
    order.value.push(data);
    sessionStorage.setItem("order", JSON.stringify(order.value));
    router.push({
      name: "orderPrimary",
    });
  }
};
const handleDrawerPageChange = (newPage) => {
  drawerCurrentPage.value = newPage;
};

// 计算抽屉中当前页的评论数据
const pagedDrawerComments = computed(() => {
  const start = (drawerCurrentPage.value - 1) * drawerPageSize.value;
  return allComments.value.slice(start, start + drawerPageSize.value);
});

const goBack=()=>{
  router.go(-1);
}
</script>

<style lang="less" scoped>
.detailView {
  width: 100%;
  display: flex;
  background-color: #e7e3e2;
  min-height: 100vh;
  flex-direction: column;
  align-items: center;
  .detailBox{
    height: 100%;
    width: 70%;
    margin-top: 30px;
    background-color: #fff;
    display: flex;
    flex-direction: column;
  }
  .detail {
    
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    

    .left {
      width: 45%;
      background-color: #fff;

      .el-carousel {
        overflow: hidden; /* 防止轮播图下方出现空白 */
      }

      .el-image {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }

    .right {
      width: 50%;
      padding: 20px; /* 添加内边距以提高可读性和美观性 */

      h1 {
        font-size: 24px;
        margin-bottom: 10px;
      }

      h2 {
        font-size: 18px;
        margin-bottom: 10px;
      }

      .attributeItem {
        display: inline-block;
        margin-right: 10px;
        margin-bottom: 10px;

        span {
          display: inline-block;
          padding: 5px 10px;
          border: 1px solid #d2c7c7;
          cursor: pointer;

          &.select_span {
            background-color: #ff5000;
            border-color: #ff5000;
          }
        }
      }

      div:not(.buttonGroup) {
        display: flex;
        align-items: center;
        margin-bottom: 10px;

        span:first-child {
          margin-right: 10px;
        }
      }
      .buttonGroup {
        display: flex;

        margin-top: 20px;
      }
    }
  }
  .middle {
    height: 100%;
    width: 70%;
    margin-top: 30px;
    display: flex;
    .comment {
      width: 100%;
      height: 100%;
      .top {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
      }
    }
  }
}
.comment-item {
  margin-top: 20px;
  .top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }
}

.price-text {
  font-size: 30px; /* 或者你想要的其他大小 */
  font-weight: bold; /* 可选：如果你还想让价格文本加粗 */
}
</style>