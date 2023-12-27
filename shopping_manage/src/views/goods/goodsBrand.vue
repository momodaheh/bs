<template>
  <div class="goodsBrand">
    <!-- 品牌card -->
    <el-card class="brandsBox">
      <template #header>
        <div class="brandHeader">
          <span>品牌</span>
          <el-button type="primary" round @click="dialogBrand = true"
            >添加</el-button
          >
        </div>
      </template>
      <el-scrollbar height="400px">
        <div
          v-for="brand in brands"
          :key="brand.id"
          class="brandItem"
          @click="getcategorys(brand.id)"
        >
          {{ brand.brand }}
          <svg
            class="icon"
            aria-hidden="true"
            @click="deleteBrandClick(brand.id)"
          >
            <use xlink:href="#icon-shanchu"></use>
          </svg>
        </div>
      </el-scrollbar>
    </el-card>

    <el-card class="categoryBox">
      <template #header>
        <div class="categoryHeader">
          <span>品牌分类</span>
          <el-button type="primary" round @click="dialogCategory = true">添加</el-button>
        </div>
      </template>
      <el-row>
        <el-col
          v-for="(category,index) in categorys"
          :key="category.id"
          :span="6"
          :offset="index %3!= 0 ? 3 : 0"
        >
          <el-card class="categoryCard">
            <div class="categoryItem">
              {{ category.category_name }}
            <svg
            class="icon"
            aria-hidden="true"
            @click="deleteCategoryClick(category.id)"
          >
            <use xlink:href="#icon-jianhao"></use>
          </svg>
            </div>
            
          </el-card>
        </el-col>
      </el-row>
    </el-card>

    <!-- 添加品牌弹窗 -->
    <el-dialog v-model="dialogBrand" title="添加品牌" width="400px">
      <el-input
        v-model="brandName"
        autocomplete="off"
        placeholder="请输入品牌名称"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogBrand = false">关闭</el-button>
          <el-button type="primary" @click="addBrandPrimary(brandName)">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 确认删除品牌弹窗 -->
    <el-dialog v-model="dialogBrandDelete" title="删除品牌" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogBrandDelete = false">关闭</el-button>
          <el-button type="primary" @click="deleteBrandPrimary(brandsId)">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 确认删除品牌的分类弹窗 -->
    <el-dialog v-model="dialogCategoryDelete" title="删除分类" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogBrandDelete = false">关闭</el-button>
          <el-button type="primary" @click="deletecategory()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 添加品牌分类弹窗 -->
    <el-dialog v-model="dialogCategory" title="添加品牌分类" width="400px">
      <el-select v-model="categoryName" >
        <el-option
          v-for="item in categoryList"
          :key="item.id"
          :label="item.category_name"
          :value="item.id"
         />
      </el-select>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogCategory = false">关闭</el-button>
          <el-button type="primary" @click="addCategory(categoryName)">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import {
  selectBrands,
  addBrand,
  deleteBrand,
  selectCategoryByBrandId,
  deletebrand_Category,
  selectCategorys,
  addbradn_Category,
} from "../../request/goods";
export default {
  setup() {
    const brands = ref([]);
    const brandName = ref("");
    const dialogBrand = ref(false);
    const dialogBrandDelete = ref(false);
    const dialogCategoryDelete=ref(false);
    const dialogCategory=ref(false);
    const brandsId = ref("");
    const categoryList=ref([]);
    const categorys = ref([]);
    const categoryId=ref('');
    const categoryName=ref('');
    onMounted(async () => {
      const response = await selectBrands();
      if (response.data.code === 0) {
        brands.value = response.data.data;
      }
      const response1=await selectCategorys();
      if (response1.data.code === 0) {
        categoryList.value = response1.data.data;
        
      }
    });

    const addBrandPrimary = async (data) => {
      await addBrand(data);
      brandName.value = "";
      dialogBrand.value = false;
      // 添加完品牌后，重新拉取品牌列表
      const response = await selectBrands();
      if (response.data.code === 0) {
        brands.value = response.data.data; // 更新品牌列表数据
      }
    };

    const deleteBrandClick = (id) => {
      // 设置要删除的品牌 ID，并打开确认删除品牌的弹窗
      brandsId.value = id;
      dialogBrandDelete.value = true;
    };

    const deleteBrandPrimary = async (data) => {
      await deleteBrand(data);
      dialogBrandDelete.value = false;
      const response = await selectBrands();
      if (response.data.code === 0) {
        brands.value = response.data.data; // 更新品牌列表数据
      }
    };

    const getcategorys = async (data) => {
      brandsId.value = data;
      await selectCategoryByBrandId(data)
        .then((result) => {
          categorys.value = result.data.data;
        })
        .catch(() => {});
    };

    const deleteCategoryClick =(data)=>{
      categoryId.value=data;
      dialogCategoryDelete.value=true;
    }

    const deletecategory =async()=>{
      await deletebrand_Category(brandsId.value,categoryId.value);
      dialogCategoryDelete.value=false;
      getcategorys(brandsId.value);
    }

    const addCategory =async(data)=>{
      await addbradn_Category(brandsId.value,data).then(()=>{
        dialogCategory.value=false;
        getcategorys(brandsId.value);
      }).catch(()=>{});


    }
    return {
      brands,
      dialogBrand,
      brandName,
      addBrandPrimary,
      deleteBrandClick,
      dialogBrandDelete,
      deleteBrandPrimary,
      brandsId,
      getcategorys,
      categorys,
      deletecategory,
      deleteCategoryClick,
      dialogCategoryDelete,
      dialogCategory,
      categoryName,
      categoryList,
      addCategory
    };
  },
};
</script>

<style lang="less" scoped>
.goodsBrand {
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
.brandsBox {
  width: 300px;
  height: 500px;

  .brandHeader {
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .brandItem {
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #d3d2d2;
  }
  .brandItem:hover {
    background-color: #d3d2d2;
  }
}

.categoryBox {
  width: 700px;
  height: 500px;
  .categoryHeader {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .categoryItem{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    .icon{
      width: 30px;
      height: 30px;
    }
  }
  .categoryCard{
    margin-top: 15px;
  }
}
</style>