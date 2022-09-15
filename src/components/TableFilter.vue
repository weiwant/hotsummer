<template>
  <div class="table-filter-wrapper">
    <div class="table-filter-section">
      <!-- 年份 -->
      <div class="table-filter-item">
        <label>年份</label>
        <el-select v-model="yearChosen" placeholder="请选择查询年份">
          <el-option
            v-for="item in options_year"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <!-- 筛选条件选择 -->
      <div class="table-filter-item nolabel">
        <button
          class="white"
          :class="{ chosen: showFilterSelectBody }"
          @click="changeFilterSelectBodyStatus"
        >
          &nbsp;添加筛选条件
        </button>
        <!-- 主体Body -->
        <transition name="fade">
          <div class="filter-select-wrapper" v-if="showFilterSelectBody">
            <!-- 类型选择 -->
            <div class="filter-select-section">
              <div class="filter-select-section-title">字段</div>
              <el-select
                v-model="filterTypeChosen"
                placeholder="请选择筛选字段"
              >
                <el-option
                  v-for="item in options_filterType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                >
                </el-option>
              </el-select>
            </div>
            <div
              class="filter-select-section"
              v-if="filterTypeChosen.length !== 0"
            >
              <!-- 值确认 -->
              <div class="filter-select-section-title">值</div>
              <!-- 下拉菜单 -->
              <el-select
                v-model="filterValue"
                placeholder="请选择"
                v-if="filterTypeChosen === 'workloadNature'"
              >
                <el-option
                  v-for="item in options_filterValue"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
              <!-- 输入框 -->
              <el-input
                v-model="filterValue"
                placeholder="请输入内容"
                v-if="filterTypeChosen === 'courseName'"
              ></el-input>
            </div>
            <!-- 添加button -->
            <div class="filter-select-section" v-if="filterValue.length !== 0">
              <div class="confirm" @click="addFilter">确认添加</div>
            </div>
          </div>
        </transition>
      </div>
      <!-- 确认查询 -->
      <div class="table-filter-item nolabel">
        <button class="green">&nbsp;查询</button>
      </div>
    </div>
    <!-- 已添加的查询条件 -->
    <div class="table-filter-section added" v-if="filterAdded.length !== 0">
      <div
        class="filter-added-item"
        v-for="(item, index) in filterAdded"
        :key="item.value"
      >
        {{ item.value }}
        <span class="delete" @click="deleteFilter(index)"></span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 年份选择
      yearChosen: this.$currentYear,
      options_year: [],
      // 添加过滤器
      showFilterSelectBody: false,
      options_filterType: [
        {
          label: "工作量性质",
          value: "workloadNature",
          disabled: false,
        },
        {
          label: "课程名称",
          value: "courseName",
          disabled: false,
        },
      ],
      filterTypeChosen: "",
      filterValue: "",
      // 已添加的过滤器
      filterAdded: [],
    };
  },
  computed: {
    options_filterValue() {
      switch (this.filterTypeChosen) {
        case "workloadNature":
          return [
            {
              label: "教学工作量",
              value: "教学工作量",
            },
            {
              label: "监考工作量",
              value: "监考工作量",
            },
            {
              label: "论文工作量",
              value: "论文工作量",
            },
          ];
        default:
          return [];
      }
    },
  },
  watch: {
    //每当选择了一个新的filter类型，value都要恢复空值
    filterTypeChosen() {
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
        type: this.filterTypeChosen,
        value: this.filterValue,
      });
      //禁用字段
      for (let item of this.options_filterType) {
        if (item.value === this.filterTypeChosen) {
          item.disabled = true;
          break;
        }
      }
      //清除历史
      this.filterTypeChosen = "";
      this.filterValue = "";
      //隐藏filter-select-body
      this.showFilterSelectBody = false;
    },
    deleteFilter(index) {
      // 解禁
      const filterType = this.filterAdded[index].type;
      for (let item of this.options_filterType) {
        if (item.value === filterType) {
          item.disabled = false;
          break;
        }
      }
      //从added数组删除
      this.filterAdded.splice(index, 1);
    },
  },
  created() {
    for (let i = 0; i < 5; i++) {
      this.options_year.push({
        label: this.$currentYear - i,
        value: this.$currentYear - i,
      });
    }
  },
};
</script>

<style scoped>
.table-filter-wrapper {
  position: relative;
  margin-bottom: 5px;
  padding: 15px 0;
}
.table-filter-section::after {
  display: block;
  content: "";
  clear: both;
}
.table-filter-item {
  float: left;
  position: relative;
  margin-right: 10px;
}
.table-filter-item.nolabel {
  top: 16px;
}
.table-filter-item label {
  display: block;
  padding-left: 2px;
  font-size: 14px;
  font-weight: 500;
}

/* 添加过滤器 */
button.white.chosen {
  background-color: #e1e1e3;
}
button.white.chosen:hover {
  background-color: white;
}
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
  gap: 10px;
}
.filter-added-item {
  margin-top: 20px;
  padding: 5px;
  color: white;
  font-size: 12px;
  font-weight: 500;
  background-color: #556672;
  border-radius: 10px;
}
.filter-added-item .delete {
  position: relative;
  top: 1px;
  cursor: pointer;
  font-family: "icomoon";
}
.filter-added-item .delete:hover {
  color: #ddd;
}
</style>