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
        <el-table-column prop="userName" label="用户名" width="100" />
        <el-table-column prop="nickName" label="用户昵称" width="150" />
        <el-table-column prop="sex" label="性别" width="100">
          <template #default="scope">
            <span>{{ formatSex(scope.row.sex) }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="telephone" label="电话号码" width="170" />
        <el-table-column prop="email" label="电子邮箱" width="170" />
        <el-table-column prop="level" label="类别" width="100" >
          <template #default="scope">
            <span>{{ formatLevel(scope.row.level) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="密码" width="120">
          <template #default="scope">
            <el-button
              v-if="scope.row.level === 1"
              type="primary"
              @click="reset(scope.row)"
              >重置密码</el-button
            >
          </template>
        </el-table-column>
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
import { selectUsers, deleteUser, resetPassword ,selectUsersByuserName} from "../request/user";
import { ElMessage } from "element-plus";
const tableData = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);
const searchKeyword = ref("");
onMounted(async () => {
  const response = await selectUsers();
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
  await deleteUser(data.id);
  const response = await selectUsers();
  if (response.data.code === 0) {
    tableData.value = response.data.data;
    // 计算总条目数
    total.value = tableData.value.length;
  }
}

const reset = async (data) => {
  await resetPassword(data.id).then((res) => {
    if (res.data.code === 0) {
      ElMessage.success("重置密码成功");
    }
  });
};

function formatSex(sex) {
  return sex === 0 ? '男' : '女';
}

function formatLevel(level) {
  return level === 1 ? '用户' : '管理员';
}

async function handleSearch() {
  let keyword = searchKeyword.value.trim();
  if (!keyword) {
    const response = await selectUsers();
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      // 计算总条目数
      total.value = tableData.value.length;
    }
  } else {
    const response = await selectUsersByuserName(keyword);
    if (response.data.code === 0) {
      tableData.value = response.data.data;
      // 计算总条目数
      total.value = tableData.value.length;
      currentPage.value = 1; // 搜索后重置为第一页
    }
  }
}
</script>

<style lang='less' scoped>
.home {
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: center;
  .goodsBox {
    width: 80%;
    height: 500px;
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