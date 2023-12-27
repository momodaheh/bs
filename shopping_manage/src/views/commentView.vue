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
        <el-table-column prop="goodsName" label="物品名" width="100" />
        <el-table-column prop="nickName" label="用户昵称" width="150" />
        <el-table-column prop="createTime" label="创建时间" width="200" />
        <el-table-column
          prop="commentType"
          label="类型"
          width="100"
          align="center"
        >
          <template #default="scope">
            <span
              :style="{
                color: scope.row.commentType === 0 ? 'green' : 'red',
              }"
            >
              {{ scope.row.commentType === 0 ? "好评" : "差评" }}
            </span>
          </template>
        </el-table-column>
        <el-table-column
          prop="content"
          label="评论内容"
          width="370"
          :show-overflow-tooltip="true"
        />
        <el-table-column label="操作">
          <template #default="scope">
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
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { selectComments, deleteComment } from "../request/user";
// import { ElMessage } from "element-plus";
const tableData = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);
const searchKeyword = ref("");
onMounted(async () => {
  const response = await selectComments();
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
  await deleteComment(data.id);
  const response = await selectComments();
  if (response.data.code === 0) {
    tableData.value = response.data.data;
    // 计算总条目数
    total.value = tableData.value.length;
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
    height: 620px;
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