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
        <el-table-column
          prop="goodsName"
          label="名称"
          width="180"
          :show-overflow-tooltip="true"
        />
        <el-table-column prop="brand" label="品牌" width="180" />
        <el-table-column prop="category_name" label="分类" width="180" />
        <el-table-column
          prop="imgShow1"
          label="图片"
          width="180"
          :show-overflow-tooltip="true"
        >
          <template #default="scope">
            <div class="itemStyle">
              <el-image v-if="scope.row.imgShow1" :src="scope.row.imgShow1" />
              <el-image v-if="scope.row.imgShow2" :src="scope.row.imgShow2" />
              <el-image v-if="scope.row.imgShow3" :src="scope.row.imgShow3" />
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button @click="handleDetail(scope.row)" type="primary">
              库存详情
            </el-button>
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
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
  <el-drawer v-model="drawer" size="60%" :title="good.goodsName">
    <el-button type="primary" @click="dialogAdd = true">添加</el-button>
    <el-table
      :data="goodsInfo"
      style="width: 100%; margin-top: 10px"
      :border="true"
      :stripe="true"
    >
      <el-table-column prop="id" label="Id" width="70" />
      <el-table-column
        prop="attribute"
        label="参数"
        width="180"
        :show-overflow-tooltip="true"
      />
      <el-table-column prop="price" label="价格" width="180" />
      <el-table-column prop="number" label="数量" width="180" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="handleUpdateGoodsInfo(scope.row)" type="primary">
            修改
          </el-button>
          <el-button @click="handleDeleteGoodsInfo(scope.row)" type="danger">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-drawer>

  <!-- 添加库存信息 -->
  <el-dialog v-model="dialogAdd" title="添加库存信息" width="400px">
    <div class="infoItem">
      <span>参数</span>
      <el-input
        v-model="info.attribute"
        autocomplete="off"
        placeholder="请输入参数"
      />
    </div>
    <div class="infoItem">
      <span>价格</span>
      <el-input
        v-model="info.price"
        autocomplete="off"
        placeholder="请输入价格"
      />
    </div>
    <div class="infoItem">
      <span>库存数量</span>
      <el-input
        v-model="info.number"
        autocomplete="off"
        placeholder="请输入数量"
      />
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogAdd = false">关闭</el-button>
        <el-button type="primary" @click="addInfo"> 确认 </el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 修改库存信息 -->
  <el-dialog v-model="dialogUpdate" title="修改库存信息" width="400px">
    <div class="infoItem">
      <span>参数</span>
      <el-input
        v-model="info.attribute"
        autocomplete="off"
        placeholder="请输入参数"
      />
    </div>
    <div class="infoItem">
      <span>价格</span>
      <el-input
        v-model="info.price"
        autocomplete="off"
        placeholder="请输入价格"
      />
    </div>
    <div class="infoItem">
      <span>库存数量</span>
      <el-input
        v-model="info.number"
        autocomplete="off"
        placeholder="请输入数量"
      />
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogUpdate = false">关闭</el-button>
        <el-button type="primary" @click="updateGoodsInfoClick">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { computed, onMounted, reactive, ref } from "vue";
import {
  selectGoods,
  deleteGood,
  selectGoodsInfoById,
  deleteGoodsInfo,
  addGoodsInfo,
  updateGoodsInfo,
  selectGoodsByName,
} from "../../request/addGoods";
const tableData = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);
const drawer = ref(false);
const dialogAdd = ref(false);
const dialogUpdate = ref(false);
const good = ref({});
const goodsInfo = ref([]);
const searchKeyword = ref("");
const info = reactive({
  id: null,
  goodsId: null,
  attribute: "",
  number: null,
  price: null,
});
onMounted(async () => {
  const response = await selectGoods();
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
  await deleteGood(data.id);
  const response = await selectGoods();
  if (response.data.code === 0) {
    tableData.value = response.data.data;
    // 计算总条目数
    total.value = tableData.value.length;
  }
}

async function handleDetail(data) {
  drawer.value = true;
  good.value = data;
  info.goodsId = data.id;
  await selectGoodsInfoById(good.value.id)
    .then((response) => {
      goodsInfo.value = response.data.data;
    })
    .catch();
}

async function handleDeleteGoodsInfo(data) {
  await deleteGoodsInfo(data.id);
  await selectGoodsInfoById(good.value.id)
    .then((response) => {
      goodsInfo.value = response.data.data;
    })
    .catch();
}

async function addInfo() {
  await addGoodsInfo(info);
  await selectGoodsInfoById(good.value.id)
    .then((response) => {
      goodsInfo.value = response.data.data;
    })
    .catch();
  dialogAdd.value = false;
  info.attribute = "";
  info.number = null;
  info.price = null;
}

function handleUpdateGoodsInfo(data) {
  dialogUpdate.value = true;
  info.attribute = data.attribute;
  info.number = data.number;
  info.price = data.price;
  info.id = data.id;
}

async function updateGoodsInfoClick() {
  await updateGoodsInfo(info);
  await selectGoodsInfoById(good.value.id)
    .then((response) => {
      goodsInfo.value = response.data.data;
    })
    .catch();
  dialogUpdate.value = false;
  info.attribute = "";
  info.number = null;
  info.price = null;
  info.id = null;
}

async function handleSearch() {
  let keyword = searchKeyword.value.trim();
  if (!keyword) {
    const response = await selectGoods();
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      // 计算总条目数
      total.value = tableData.value.length;
    }
  } else {
    const response = await selectGoodsByName(keyword);
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      // 计算总条目数
      total.value = tableData.value.length;
      currentPage.value = 1; // 搜索后重置为第一页
    }
  }
}
</script>

<style lang="less" scoped>
.home {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: center;
  .goodsBox {
    width: 80%;
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
.el-image {
  display: block;
  width: 50px;
  height: 65px;
  object-fit: cover; /* 确保图片能够适应其容器 */
}
.itemStyle {
  display: flex;
  flex-direction: row;
}
</style>