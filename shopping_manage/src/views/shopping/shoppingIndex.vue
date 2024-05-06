<template>
  <div class="spIndex">
    <div class="top">
      <div class="top_carousel">
        <el-carousel indicator-position="outside" height="300px">
          <el-carousel-item>
            <el-image :src="require('@/assets/img/1.jpg')" fit="cover" />
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="require('@/assets/img/2.jpg')" fit="cover" />
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="require('@/assets/img/3.jpg')" fit="cover" />
          </el-carousel-item>
          <el-carousel-item>
            <el-image :src="require('@/assets/img/4.jpg')" fit="cover" />
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <div class="brandBox">
      <el-row :gutter="20">
        <el-col v-for="item in brandList" :key="item.id" :span="4">
          <el-dropdown>
            <span>{{ item.brand }}</span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item
                  v-for="dropdownItem in item.dropdownItems"
                  :key="dropdownItem.id"
                  @click="handleDropdownItemClick(item.id, dropdownItem.id)"
                >
                  {{ dropdownItem.category_name }}
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
      </el-row>
    </div>
    <div class="middle">
      <div class="middleItem">
        <div class="item_top">
          <h3>手机</h3>
          <span @click="morePhone">更多></span>
        </div>
        <el-row :gutter="20">
          <el-col
            v-for="item in phoneList.slice(0, 4)"
            :key="item.id"
            :span="6"
          >
            <el-card>
              <div class="item_card" @click="showDetail(item.id)">
                <img :src="item.imgShow1" />
                <span>{{ item.goodsName }}</span>
                <span class="priceStyle">￥{{ item.minprice }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

      <div class="middleItem" style="margin-top: 30px">
        <div class="item_top">
          <h3>电脑</h3>
          <span @click="moreCom">更多></span>
        </div>
        <el-row :gutter="20">
          <el-col v-for="item in comList.slice(0, 4)" :key="item.id" :span="6">
            <el-card>
              <div class="item_card" @click="showDetail(item.id)">
                <img :src="item.imgShow1" />
                <span>{{ item.goodsName }}</span>
                <span class="priceStyle">￥{{ item.minprice }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

      <div class="middleItem" style="margin-top: 30px">
        <div class="item_top">
          <h3>电视</h3>
          <span @click="moreWatch">更多></span>
        </div>
        <el-row :gutter="20">
          <el-col v-for="item in watchList.slice(0, 4)" :key="item.id" :span="6">
            <el-card>
              <div class="item_card" @click="showDetail(item.id)">
                <img :src="item.imgShow1" />
                <span>{{ item.goodsName }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import { onMounted, ref } from "vue";
import { useStore } from "vuex";
import { selectGoodsPhone, selectGoodsCom ,selectGoodsWatch} from "../../request/addGoods";
import {
  selectBrands,
  selectCategoryByBrandId,
  selectByBrandCotegory,
} from "../../request/goods";
const phoneList = ref([]);
const comList = ref([]);
const watchList = ref([]);
const store = useStore();
const brandList = ref([]);
onMounted(async () => {
  await selectGoodsPhone()
    .then((res) => {
      phoneList.value = res.data.data;
    })
    .catch();
  await selectGoodsCom()
    .then((res) => {
      comList.value = res.data.data;
    })
    .catch();
    await selectGoodsWatch()
    .then((res) => {
      watchList.value = res.data.data;
    })
    .catch();
  // 获取所有品牌列表
  const brandsResponse = await selectBrands();
  brandList.value = brandsResponse.data.data;

  // 对每个品牌异步获取其下拉菜单项
  for (const brand of brandList.value) {
    const dropdownItemsResponse = await selectCategoryByBrandId(brand.id);
    brand.dropdownItems = dropdownItemsResponse.data.data;
  }
  console.log(brandList.value);
});

const showDetail = (item) => {
  sessionStorage.setItem("goodsId", item);
  router.push({
    name: "goodDetail",
  });
};

const morePhone = () => {
  store.commit("setGoodsList", phoneList.value);
  router.push({
    name: "goodShopping",
  });
};

const moreCom = () => {
  store.commit("setGoodsList", comList.value);
  router.push({
    name: "goodShopping",
  });
};

const moreWatch = () => {
  store.commit("setGoodsList", watchList.value);
  router.push({
    name: "goodShopping",
  });
};
const handleDropdownItemClick = async (brandId, categoryId) => {
  await selectByBrandCotegory(brandId, categoryId).then((res) => {
    store.commit("setGoodsList", res.data.data);
  });
  router.push({
    name: "goodShopping",
  });
};
</script>

<style lang="less" scoped>
.spIndex {
  width: 100%;
  display: flex;
  background-color: #f5f5f5;
  height: 100%;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .top {
    width: 70%;
    min-height: 400px;
    min-width: 700px;
    margin-top: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    .top_carousel {
      width: 80%;
      margin-top: 20px;
      .el-image {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover; /* 确保图片能够适应其容器 */
      }
    }
  }
  .brandBox {
    display: flex;
    width: 70%;
    margin-top: 20px;
    .el-row {
      width: 100%;
      height: 30px;
      margin-left: 0 !important;
      margin-right: 0 !important;
      .el-col {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #2b2e31;
        span {
          color: #fff;
          font-size: 18px;
        }
        
      }
    }
    .el-dropdown-menu {
          width: 100px ;
        }
  }
  .middle {
    display: flex;
    flex-direction: column;
    margin-top: 20px;
    background-color: #fff;
    width: 70%;
    .middleItem {
      .item_top {
        display: flex;
        flex-direction: row;
        align-items: center;
        background: linear-gradient(to right, #f16909, #67c23a);
        justify-content: space-between;
        height: 50px;
        h3 {
          margin-left: 20px;
        }
        span {
          margin-right: 20px;
        }
      }
      img {
        width: 200px;
        height: 250px;
      }
      .item_card {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }
    }
  }
  .priceStyle {
    font-size: 24px; /* 或者你想要的其他大小 */
    font-weight: bold; /* 可选：如果你还想让价格文本加粗 */
    color: #fd423e;
  }
}
</style>