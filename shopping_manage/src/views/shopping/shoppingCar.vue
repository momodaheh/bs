<template>
  <div class="shoppingCarView">
    <div class="carBox">
      <el-card class="car">
        <template #header>
          <div class="top">
            <el-page-header @back="goBack" title=" ">
              <template #content>
                <h2>购物车</h2>
              </template>
            </el-page-header>
            <h3>{{ totalPrice }}元</h3>
          </div>
        </template>
        <el-table
          :data="carList"
          @selection-change="handleSelectionChange"
          :stripe="true"
        >
          <el-table-column type="selection" width="50"></el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="50"
          ></el-table-column>
          <el-table-column
            label="商品信息"
            min-width="350px"
            header-align="center"
          >
            <template #default="scope">
              <div class="itemStyle" @click="showDetail(scope.row.goodsId)">
                <el-image :src="scope.row.imgShow1" />
                <span>{{ scope.row.goodsName }}</span>
                <span>{{ scope.row.attribute }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="价格" prop="price" width="100px">
          </el-table-column>
          <el-table-column label="数量" width="150px" header-align="center">
            <template #default="scope">
              <el-input-number  v-model="scope.row.amount" style="width: 100px" />
            </template>
          </el-table-column>
          <el-table-column label="金额" width="100px">
            <template #default="scope">
              {{ scope.row.price * scope.row.amount }}元
            </template>
          </el-table-column>
          <el-table-column label="操作" width="100px">
            <template #default="scope">
              <el-button type="danger" @click="deleteButtonClick(scope.row.id)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>

        <div class="bottom">
          <div>
            <el-button type="danger" @click="batchDelete">删除</el-button>
          </div>
          <div class="bottom_right">
            <h3>{{ totalPrice }}元</h3>
            <el-button type="primary" @click="submitOrders">结算</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { selectShoppingCarById, deleteShoppingCar } from "../../request/order";
import { ElMessage } from "element-plus";
import router from "@/router";
const userId = ref(null);
const carList = ref([]);
// const order=reactive({
//   totalPrice:0
// })
onMounted(async () => {
  userId.value = sessionStorage.getItem("userId");
  await selectShoppingCarById(userId.value).then((res) => {
    carList.value = res.data.data.map((item) => ({ ...item, selected: false }));
  });
});

const handleSelectionChange = (selectedRows) => {
  if (selectedRows.length === 0) {
    carList.value.forEach((item) => {
      item.selected = false;
    });
  } else {
    carList.value.forEach((item) => {
      item.selected = selectedRows.includes(item);
    });
  }
};

const selectedGoods = computed(() => {
  return carList.value.filter((item) => item.selected);
});

const totalPrice = computed(() => {
  return selectedGoods.value.reduce(
    (accumulator, current) => accumulator + current.price * current.amount,
    0
  );
});

const deleteButtonClick = async (data) => {
  await deleteShoppingCar(data);
  await selectShoppingCarById(userId.value).then((res) => {
    carList.value = res.data.data.map((item) => ({ ...item, selected: false }));
  });
  ElMessage.success("删除成功");
};

const batchDelete = async () => {
  const selectedGoodsIds = selectedGoods.value.map((item) => item.id);
  for (const id of selectedGoodsIds) {
    await deleteShoppingCar(id);
  }

  await selectShoppingCarById(userId.value).then((res) => {
    carList.value = res.data.data.map((item) => ({ ...item, selected: false }));
  });
};

const submitOrders = async () => {
  // 创建一个新的数组selectedOrders，用于存储选中商品的订单信息
  const selectedOrders = [];

  // 遍历selectedGoods，为每个选中的商品创建一个订单对象，并将其添加到selectedOrders数组中
  selectedGoods.value.forEach((item) => {
    selectedOrders.push({
      userId: userId.value,
      goodId: item.goodId,
      goodInfoId: item.goodInfoId,
      price: item.price,
      amount: item.amount,
      addressId: sessionStorage.getItem("selectedAddressId"), // 假设你在选择地址后将地址ID存储到了sessionStorage中
    });

    // 删除购物车中的选中商品
    deleteShoppingCar(item.id);
  });

  await selectShoppingCarById(userId.value).then((res) => {
    carList.value = res.data.data.map((item) => ({ ...item, selected: false }));
  });

  // 将selectedOrders数组存储到sessionStorage中
  sessionStorage.setItem("order", JSON.stringify(selectedOrders));

  router.push({ name: "orderPrimary" }); // 跳转到确认订单页面
};

const showDetail = (item) => {
  sessionStorage.setItem("goodsId", item);
  router.push({
    name: "goodDetail",
  });
};

const goBack=()=>{
  router.go(-1);
}
</script>


<style lang="less" scoped>
.shoppingCarView {
  width: 100%;
  display: flex;
  background-color: #e7e3e2;
  height: 100%;
  min-height: 100vh;
  flex-direction: column;
  align-items: center;
  .carBox {
    width: 70%;
    height: 100%;
    margin-top: 20px;
    .car {
      .top {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        h3 {
          color: #ff5000;
        }
      }
      .bottom {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        margin-top: 20px;
        h3 {
          color: #ff5000;
        }
        .bottom_right {
          display: flex;
          flex-direction: row;
          align-items: center;
          h3 {
            margin-right: 30px;
          }
        }
      }
      .itemStyle {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        align-items: center;
      }
      .el-image {
        display: block;
        width: 70px;
        height: 90px;
        object-fit: cover; /* 确保图片能够适应其容器 */
      }
    }
  }
}
</style>