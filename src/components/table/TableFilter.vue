<template>
  <div class="table-filter-wrapper">
    <div class="table-filter-section">
      <!-- 年份 -->
      <div class="table-filter-item">
        <label>年份</label>
        <el-date-picker v-model="yearChosen" type="year" placeholder="选择自然年份" value-format="yyyy" :editable="false"
          :disabled="!isEditing">
        </el-date-picker>
      </div>
      <!-- 筛选条件选择 -->
      <div class="table-filter-item nolabel">
        <button class="white" :class="{ chosen: showFilterSelectBody }" @click="changeFilterSelectBodyStatus"
          :disabled="!isEditing">
          &nbsp;添加筛选条件
        </button>
        <!-- Body -->
        <transition name="fade">
          <div class="filter-select-wrapper" v-if="showFilterSelectBody">
            <!-- 类型选择 -->
            <div class="filter-select-section">
              <div class="filter-select-section-title">字段</div>
              <el-select v-model="filterIndexChosen" placeholder="请选择筛选字段">
                <el-option v-for="(item, index) in filters" :key="item.type_filter" :label="item.label" :value="index"
                  :disabled="
                    filterAdded.findIndex(
                      (el) => el.type === item.type_filter
                    ) !== -1
                  ">
                </el-option>
              </el-select>
            </div>
            <div class="filter-select-section" v-if="filterTypeChosen.length !== 0">
              <!-- 值确认 -->
              <div class="filter-select-section-title">值</div>
              <!-- 下拉菜单 -->
              <el-select v-model="filterValue" placeholder="请选择"
                v-if="filters[filterIndexChosen].type_input === 'select'">
                <el-option v-for="item in filters[filterIndexChosen].options" :key="item" :label="item" :value="item">
                </el-option>
              </el-select>
              <!-- 输入框 -->
              <el-input v-model="filterValue" placeholder="请输入内容"
                v-if="filters[filterIndexChosen].type_input === 'text'"></el-input>
            </div>
            <!-- 添加button -->
            <div class="filter-select-section" v-if="filterValue.length !== 0">
              <div class="confirm" @click="addFilter">确认添加</div>
            </div>
          </div>
        </transition>
      </div>
      <!-- 确认查询/重置查询条件 -->
      <div class="table-filter-item nolabel">
        <button class="green" @click="search" v-html='isEditing ? "&nbsp;确认查询" : "&nbsp;重置查询条件"'>
        </button>
      </div>
    </div>
    <!-- 已添加的查询条件 -->
    <div class="table-filter-section added">
      <button class="transparent_red" :disabled="!isEditing || filterAdded.length <= 0" @click="clearAdded">
        
      </button>
      <div class="filter-added-item" v-for="(item, index) in filterAdded" :key="item.filter_type"
        :style="{ backgroundColor: !isEditing ? '#ddd' : colorList[index % 4] }">
        {{ item.value }}
        <span class="delete" :class="{ 'disabled': !isEditing }" @click="deleteFilter(index)"></span>
      </div>
    </div>
  </div>
</template>

<script>
import { Message } from 'element-ui'
export default {
  props: {
    filters: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      // 年份选择
      yearChosen: `${this.$store.getters.currentYear}`,
      // 添加过滤器
      showFilterSelectBody: false,
      filterIndexChosen: "",
      filterValue: "",
      // 已添加的过滤器
      filterAdded: [],
      colorList: ["#b9ca6e", "#87a863", "#5a813b", "#46693a"],
      //是否正在重置查询条件
      isEditing: false,
    };
  },
  computed: {
    filterTypeChosen() {
      if (
        typeof this.filterIndexChosen !== "number" ||
        this.filterIndexChosen < 0
      ) {
        return "";
      }
      return this.filters[this.filterIndexChosen].type_filter;
    },
  },
  watch: {
    //每当选择了一个新的filter类型，value都要恢复空值
    filterIndexChosen() {
      this.filterValue = "";
    },
  },
  methods: {
    changeFilterSelectBodyStatus() {
      this.showFilterSelectBody = !this.showFilterSelectBody;
    },
    addFilter() {
      //加入added数组
      this.filterAdded.push({
        type: this.filterTypeChosen,  //type_filter
        value: this.filterValue,
      });
      //清除历史
      this.filterIndexChosen = "";
      this.filterValue = "";
      //隐藏filter-select-body
      this.showFilterSelectBody = false;
    },
    deleteFilter(index) {
      if (!this.isEditing) return;
      //从added数组删除
      this.filterAdded.splice(index, 1);
    },
    clearAdded() {
      this.filterAdded = [];
    },
    search() {
      if (this.isEditing) {  //点击确认查询
        if (this.yearChosen === '' || this.yearChosen === null) {
          Message({
            message: "年份设置不可为空",
            type: 'error',
            duration: 2000
          })
        } else {
          this.$emit("search", this.yearChosen, this.filterAdded);
          this.isEditing = false;
        }
      } else {   //点击重置查询条件
        this.isEditing = true;
      }
    },
  },
};
</script>

<style scoped>
.table-filter-wrapper {
  position: relative;
  margin-bottom: 5px;
  padding: 15px 0;
}

.table-filter-section{
  display: flex;
  flex-wrap: wrap;
  padding-bottom: 10px;
}

.table-filter-item {
  position: relative;
  margin-right: 10px;
}

.table-filter-item.nolabel { 
  /* button没有el-select上方的label，对齐需要移动一下  */
  top: 16px;
}

.table-filter-item label {
  display: block;
  padding-left: 2px;
  font-size: 14px;
  font-weight: 500;
}

/* 添加过滤器按钮 */
button.white.chosen {
  background-color: #e1e1e3;
}

button.white.chosen:hover {
  background-color: white;
}
/* 添加表单 */
.filter-select-wrapper {
  position: absolute;
  top: 45px;
  z-index: 2;
  padding-bottom: 10px;
  width: 200px;
  background-color: white;
  border: 1px solid #dddfe5;
  border-radius: 5px;
}
/* 已添加的查询条件 */
.filter-select-section {
  padding: 10px;
}

.filter-select-section::after {
  display: block;
  content: "";
  clear: both;
}

.filter-select-section-title {
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 5px;
}

.filter-select-section .confirm {
  float: right;
  color: #447161;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
}

.filter-select-section .confirm:hover {
  color: #243a32;
}

/* 已选择的过滤器 */
.table-filter-section.added {
  display: flex;
  margin-top: 15px;
  gap: 10px;
  align-items: center;
}

.filter-added-item {
  padding: 5px;
  color: white;
  font-size: 12px;
  font-weight: 500;
  border-radius: 10px;
  transition: all 0.2s;
}

.filter-added-item .delete {
  position: relative;
  top: 1px;
  cursor: pointer;
  font-family: "icomoon";
}

.filter-added-item .delete:hover {
  color: #ba9291;
}

.filter-added-item .delete.disabled:hover {
  color: white;
  cursor: not-allowed;
}
</style>