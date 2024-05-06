<template>
  <div class="home">
    <div class="goodsBox">
      <div class="searchBox">
        <el-input
          v-model="searchKeyword"
          placeholder="请输入关键词搜索"
          @keyup.enter="handleSearch"
          clearable
        />
        <el-button type="primary" @click="handleSearch">搜索</el-button>
      </div>
      <el-table
        :data="pagedData"
        style="width: 100%"
        :border="true"
        :stripe="true"
        class="tableStyle"
      >
        <el-table-column prop="id" label="Id" width="70" />
        <el-table-column prop="nickName" label="用户昵称" width="100" />
        <el-table-column
          label="商品信息"
          width="250px"
          header-align="center"
          :show-overflow-tooltip="true"
        >
          <template #default="scope">
            <div class="itemStyle">
              <el-image :src="scope.row.imgShow1" />
              <span>{{ scope.row.goodsName }}</span>
              <span>{{ scope.row.attribute }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column
          label="收货地址"
          width="200px"
          header-align="center"
          :show-overflow-tooltip="true"
        >
          <template #default="scope">
            <div class="itemStyle2">
              <span>{{ scope.row.contact }}</span>
              <span>{{ scope.row.telephone }}</span>
              <span>{{ scope.row.address }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="number" label="数量" width="70" />
        <el-table-column prop="price" label="总价" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="100" />
        <el-table-column prop="updateTime" label="修改时间" width="100" />
        <el-table-column prop="payId" label="支付订单" width="100" />
        <el-table-column prop="logisticsId" label="物流单号" width="100" />
        <el-table-column prop="state" label="状态" width="100">
          <template #default="scope">
            <span>{{ formatState(scope.row.state) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <!-- <el-button  type="primary">
              库存详情
            </el-button> -->
            <el-button @click="handleDelete(scope.row)" type="danger">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 30, 40]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { selectOrders, deleteOrder ,selectOrdresById} from "../../request/order";
const tableData = ref([]);
const currentPage = ref(1);
const pageSize = ref(5);
const total = ref(0);
const searchKeyword = ref("");
onMounted(async () => {
  const response = await selectOrders();
  if (response.data.code === 0) {
    tableData.value = response.data.data;
    // 计算总条目数
    total.value = tableData.value.length;
  }
});

const pagedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  return tableData.value.slice(start, start + pageSize.value);
});

function handleSizeChange(newPageSize) {
  currentPage.value = 1; // 切换每页大小时重置当前页码为 1
  pageSize.value = parseInt(newPageSize);
}

function handleCurrentChange(pageNum) {
  currentPage.value = pageNum;
}

async function handleDelete(data) {
  await deleteOrder(data.id);
  const response = await selectOrders();
  if (response.data.code === 0) {
    tableData.value = response.data.data;
    // 计算总条目数
    total.value = tableData.value.length;
  }
}

function formatState(state) {
  switch (state) {
    case 0:
      return '待支付';
    case 1:
      return '已支付，待发货';
    case 2:
      return '已发货，待收货';
    case 3:
      return '交易完成';
    default:
      return '未知状态';
  }
}

async function handleSearch() {
  const keyword = searchKeyword.value.trim();
  
  if (!keyword) {
    // 如果关键词为空，则加载所有数据
    const response = await selectOrders();
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      total.value = tableData.value.length;
    }
  } else {
    // 如果有关键词，则发送带有关键词的请求进行搜索
    const response = await selectOrdresById(keyword);
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      total.value = tableData.value.length;
    }
  }

  currentPage.value = 1; // 搜索后重置当前页码为第一页
}
</script>

<style lang="less" scoped>
.home {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: center;
  .goodsBox {
    // width: 80%;
    height: 600px;
    display: flex;
    flex-direction: column;
    align-items: center;
    .searchBox {
      display: flex;
      align-items: center;
      flex-direction: row;
      justify-content: center;
    }
    .tableStyle {
      margin-top: 20px;
    }
  }
}
.itemStyle {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  .el-image {
  display: block;
  width: 70px;
  height: 80px;
  object-fit: cover; /* 确保图片能够适应其容器 */
  
}
}
.itemStyle2 {
  display: flex;
  flex-direction: column;
}
.el-image {
  display: block;
  min-width: 70px;
  min-height: 80px;
  object-fit: cover; /* 确保图片能够适应其容器 */
}
.home {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: center;
  .goodsBox {
    width: 100%;

    display: flex;
    flex-direction: column;
    align-items: center;
    .searchBox {
      display: flex;
      align-items: center;
      flex-direction: row;
      justify-content: center;
    }
    .tableStyle {
      margin-top: 20px;
    }
  }
}
</style>