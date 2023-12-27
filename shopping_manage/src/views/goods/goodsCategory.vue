<template>
  <div class="goodsCategory">
    <el-card class="categoryBox">
      <template #header>
        <div class="Header">
          <span>分类</span>
          <el-button type="primary" round @click="dialogCategory = true"
            >添加</el-button
          >
        </div>
      </template>
      <el-scrollbar height="400px">
        <div
          v-for="category in categoryList"
          :key="category.id"
          class="categoryItem"
          :class="{ active: category.id === categoryId }"
          @click="selectAttribute(category.id)"
        >
          {{ category.category_name }}
          <svg
            class="icon"
            aria-hidden="true"
            @click="deleteCategorybutton(category.id)"
          >
            <use xlink:href="#icon-shanchu"></use>
          </svg>
        </div>
      </el-scrollbar>
    </el-card>

    <el-card class="AttributeBox">
      <template #header>
        <div class="Header">
          <span>属性</span>
          <el-button type="primary" round @click="dialogAttribute = true"
            >添加</el-button
          >
        </div>
      </template>
      <el-scrollbar height="400px">
        <div
          v-for="attribute in attributeList"
          :key="attribute.id"
          class="categoryItem"
          :class="{ active: attribute.id === attributeId }"
          @click="selectAttributeValue(attribute.id)"
        >
          {{ attribute.attribute_name }}
          <svg
            class="icon"
            aria-hidden="true"
            @click="deleteAttributeClick(attribute.id)"
          >
            <use xlink:href="#icon-shanchu"></use>
          </svg>
        </div>
      </el-scrollbar>
    </el-card>

    <el-card class="AttributeValueBox">
      <template #header>
        <div class="Header">
          <span>属性值</span>
          <el-button type="primary" round @click="dialogAttributeValue=true">添加</el-button>
        </div>
      </template>
      <el-scrollbar height="400px">
        <el-row>
          <el-col
            v-for="(attributeValue, index) in attributeValueList"
            :key="attributeValue.id"
            :span="6"
            :offset="index %3!= 0 ? 3 : 0"
          >
            <el-card class="attributeValueCard">
              <div class="attributeItem">
                {{ attributeValue.attribute_value }}
                <svg
                  class="icon"
                  aria-hidden="true"
                  @click="deleteAttributeValueClick(attributeValue.id)"
                >
                  <use xlink:href="#icon-jianhao"></use>
                </svg>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-scrollbar>
    </el-card>

    <!-- 添加分类弹窗 -->
    <el-dialog v-model="dialogCategory" title="添加分类" width="400px">
      <el-input
        v-model="categoryName"
        autocomplete="off"
        placeholder="请输入分类名称"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogCategory = false">关闭</el-button>
          <el-button type="primary" @click="addCategoryClick()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 确认删除分类的分类弹窗 -->
    <el-dialog v-model="dialogCategoryDelete" title="删除分类" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogBrandDelete = false">关闭</el-button>
          <el-button type="primary" @click="deleteCategoryClick()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 添加分类属性弹窗 -->
    <el-dialog v-model="dialogAttribute" title="添加属性" width="400px">
      <el-input
        v-model="attributeName"
        autocomplete="off"
        placeholder="请输入属性名称"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogAttribute = false">关闭</el-button>
          <el-button type="primary" @click="addAttributeButton()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 确认删除属性的分类弹窗 -->
    <el-dialog v-model="dialogAttributeDelete" title="删除属性" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogAttributeDelete = false">关闭</el-button>
          <el-button type="primary" @click="deleteAttributeButton()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 添加属性值弹窗 -->
    <el-dialog v-model="dialogAttributeValue" title="添加属性值" width="400px">
      <el-input
        v-model="attributeValue"
        autocomplete="off"
        placeholder="请输入属性值"
      />
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogAttributeValue = false">关闭</el-button>
          <el-button type="primary" @click="addAttributeValueClick()">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

       <!-- 确认删除属性值弹窗 -->
    <el-dialog v-model="dialogAttributeValueDelete" title="删除属性值" width="400px">
      <span>请确认是否删除</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogAttributeValueDelete = false">关闭</el-button>
          <el-button type="primary" @click="deleteAttributeValueButton()">
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
  selectCategorys,
  addcategory,
  deletecategory,
  selectAttributeByCategoryId,
  addAttribute,
  deleteAttribute,
  selectAttributeValueById,
  addAttributeValue,
  deleteAttributeValue
} from "../../request/goods";
export default {
  setup() {
    const categoryList = ref([]);
    const dialogCategory = ref(false);
    const categoryName = ref("");
    const dialogCategoryDelete = ref(false);
    const categoryId = ref("");
    const attributeList = ref([]);
    const dialogAttribute = ref(false);
    const attributeName = ref("");
    const dialogAttributeDelete = ref(false);
    const attributeId = ref("");
    const attributeValueList = ref([]);
    const dialogAttributeValue =ref(false);
    const attributeValue=ref('');
    const dialogAttributeValueDelete=ref(false);
    const attributeValueId=ref('')
    onMounted(async () => {
      const response = await selectCategorys();
      if (response.data.code === 0) {
        categoryList.value = response.data.data;
      }
    });

    const addCategoryClick = async () => {
      await addcategory(categoryName.value);
      const response = await selectCategorys();
      if (response.data.code === 0) {
        categoryList.value = response.data.data;
      }
      dialogCategory.value = false;
      categoryName.value = "";
    };

    const deleteCategorybutton = (data) => {
      categoryId.value = data;
      dialogCategoryDelete.value = true;
    };

    const deleteCategoryClick = async () => {
      await deletecategory(categoryId.value);
      const response = await selectCategorys();
      if (response.data.code === 0) {
        categoryList.value = response.data.data;
      }
      dialogCategoryDelete.value = false;
    };

    const selectAttribute = async (data) => {
      categoryId.value = data;
      await selectAttributeByCategoryId(categoryId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeList.value = result.data.data;
          }
        })
        .catch(() => {});
    };

    const addAttributeButton = async () => {
      await addAttribute(categoryId.value, attributeName.value);
      await selectAttributeByCategoryId(categoryId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeList.value = result.data.data;
          }
        })
        .catch(() => {});
      dialogAttribute.value = false;
      attributeName.value = "";
    };

    const deleteAttributeClick = async (data) => {
      attributeId.value = data;
      dialogAttributeDelete.value = true;
    };

    const deleteAttributeButton = async () => {
      await deleteAttribute(attributeId.value);
      await selectAttributeByCategoryId(categoryId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeList.value = result.data.data;
          }
        })
        .catch(() => {});
      dialogAttributeDelete.value = false;
    };

    const selectAttributeValue = async (data) => {
      attributeId.value = data;
      await selectAttributeValueById(attributeId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeValueList.value = result.data.data;
          }
        })
        .catch(() => {});
    };

    const addAttributeValueClick=async()=>{
      await addAttributeValue(attributeId.value,attributeValue.value);
      await selectAttributeValueById(attributeId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeValueList.value = result.data.data;
          }
        })
        .catch(() => {});
        attributeValue.value='';
        dialogAttributeValue.value=false;
    }

    const deleteAttributeValueClick=(data)=>{
      attributeValueId.value=data;
      dialogAttributeValueDelete.value=true;
    }

    const deleteAttributeValueButton=async()=>{
      await deleteAttributeValue(attributeValueId.value)
       await selectAttributeValueById(attributeId.value)
        .then((result) => {
          if (result.data.code === 0) {
            attributeValueList.value = result.data.data;
          }
        })
        .catch(() => {});
        dialogAttributeValueDelete.value=false;
    }
    return {
      categoryList,
      dialogCategory,
      categoryName,
      addCategoryClick,
      dialogCategoryDelete,
      deleteCategorybutton,
      deleteCategoryClick,
      attributeList,
      selectAttribute,
      categoryId,
      dialogAttribute,
      attributeName,
      addAttributeButton,
      dialogAttributeDelete,
      attributeId,
      deleteAttributeClick,
      deleteAttributeButton,
      attributeValueList,
      selectAttributeValue,
      dialogAttributeValue,
      addAttributeValueClick,
      attributeValue,
      dialogAttributeValueDelete,
      attributeValueId,
      deleteAttributeValueClick,
      deleteAttributeValueButton
    };
  },
};
</script>

<style lang="less" scoped>
.goodsCategory {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: row;
  .categoryBox {
    width: 200px;
    height: 500px;
    .categoryItem {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
    }
    .categoryItem.active {
      background-color: #d3d2d2;
    }
    .categoryItem:hover {
      background-color: #d3d2d2;
    }
  }
  .AttributeBox {
    width: 200px;
    height: 500px;
    .categoryItem {
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
    }
    .categoryItem.active {
      background-color: #d3d2d2;
    }
    .categoryItem:hover {
      background-color: #d3d2d2;
    }
  }
  .AttributeValueBox {
    width: 700px;
    height: 500px;
    .attributeItem{
      display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    .icon{
      width: 30px;
      height: 30px;
    }
    }
    .attributeValueCard{
      margin-top: 15px;
    }
  }
}
.Header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>