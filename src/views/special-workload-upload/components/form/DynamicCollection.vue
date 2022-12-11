<template>
  <div id="dynamic-collection">
    <ul class="teachers-list">
      <li class="teachers-list-item" v-for="(teacher, index) in teachers" :key="teacher.account">
        <div class="basic-info">
          <div class="info-item">
            <div class="name">{{ teacher.name }}</div>
            <div class="account">{{ teacher.account }}</div>
          </div>
          <div class="info-item order">
            <label>排序 <span class="icon" v-if="index === 0" @mouseenter="() => { showExplaination = true }"
                @mouseleave="showExplaination = false"></span>
              <transition name="fadeIn">
                <span class="explaination"
                  v-show="index === 0 && showExplaination">项目负责人排序为0；参与人序号大于0；参与人排序越大，权重越低。</span>
              </transition>
            </label>

            <el-input type="number" placeholder="请设置参与人排序" v-model="teacher.authorOrder" size="mini" min="0" step="1">
            </el-input>
          </div>
        </div>
        <div class="delete">
          <button type='button' class="delete-btn noBorder red" @click="removeTeacher(index)"></button>
        </div>
      </li>
    </ul>
    <div class="add ">
      <div class="add-btn">
        <button class="withBorder green" @click="() => { showSearchBar = true }" :disabled="disabled">添加&nbsp;<span
            class="icon"></span></button>
      </div>
      <div class="search-bar" v-if="showSearchBar">
        <el-input placeholder="请输入职工姓名/职工号，回车查询" v-model="searchValue" prefix-icon="el-icon-search"
          @keyup.native.enter="getTeacherListData">
        </el-input>
      </div>
      <div class="search-result">
        <i class="el-icon-loading" v-show="showLoading"></i>
        <el-table :data="teacherListData" style="width: 100%" size="mini"
          v-if="showTeacherList && teacherListData.length !== 0">
          <el-table-column prop="name" label="姓名" width="100">
          </el-table-column>
          <el-table-column prop="account" label="职工号">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button :disabled="alreadyExist(scope.row)" @click="addTeacher(scope.row)" type="text" size="small">
                {{ alreadyExist(scope.row) ? '已添加' : '添加' }}</el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>
    </div>
  </div>
</template>
<script>
import { getTeacherListData } from '@/api/special-workload/upload'
export default {
  name: "DynamicCollection",
  props: ['disabled'],
  data() {
    return {
      showExplaination: false,  //关于“排序“概念的解释
      //选中的参与人员
      teachers: [],
      showSearchBar: false,
      searchValue: '',
      showTeacherList: false,
      showLoading: false,
      teacherListHeaders: this.$store.getters.teacherListHeaders_special,
      //添加参与人时的查询结果
      teacherListData: [
        {
          name: '张三',
          account: '11111111111111111'
        },
        {
          name: '李四',
          account: '1111111444444441'
        },
      ],

    }
  },
  methods: {
    addTeacher(target) {
      this.showTeacherList = false;
      this.showSearchBar = false;
      this.searchValue = "";  //
      //添加，一定要是深复制
      let value = JSON.parse(JSON.stringify(target));
      value['authorOrder'] = ''   //添加排序属性
      this.teachers.push(value)
    },
    removeTeacher(index) {
      this.teachers.splice(index, 1);
    },
    //判断查询结果中的项目是否已经被添加
    alreadyExist(target) {
      const account = target.account
      for (let item of this.teachers) {
        if (item.account === account) return true;
      }
      return false;
    },
    getTeacherListData() {
      this.showLoading = true;
      //调api
      setTimeout(() => {
        this.showLoading = false
        this.showTeacherList = true;
      }, 1000)
    }
  },

}
</script>
<style scoped>
/* 已添加列表 */
.teachers-list-item {
  line-height: 1;
  position: relative;
  margin-top: 10px;
}

.teachers-list-item:first-child {
  margin-top: 0;
}

.teachers-list-item .basic-info {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f3f7f6;
  padding: 8px 20px;
  border-radius: 5px;
  max-width: 80%;
}

.teachers-list-item .basic-info .info-item {
  display: inline-block;
}

.teachers-list-item .basic-info .name {
  padding-bottom: 8px;
  font-weight: 500;
  color: #666;
  font-size: 13px;
}

.teachers-list-item .basic-info .account {
  font-size: 12px;
  color: #aaa;
}

.teachers-list-item button.delete {
  position: absolute;
  padding: 0;
  right: -50px;
  top: 0;
  line-height: 51px;
  font-size: 25px;
}

.teachers-list-item .info-item.order label {
  display: block;
  position: relative;
  margin-bottom: 5px;
  font-weight: 500;
  color: #666;
  font-size: 13px;
}

.teachers-list-item .info-item.order .icon {
  font-family: 'icomoon';
  color: rgb(201, 176, 64);
}

.teachers-list-item .info-item.order .explaination {
  position: absolute;
  top: -45px;
  left: 22px;
  color: #999;
  line-height: 1.1;
  width: 250px;
  padding: 5px 10px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  word-wrap: keep-all;
  font-size: 12px;
  font-weight: 400;

}

.teachers-list-item .info-item.order .explaination::after {
  content: '';
  display: block;
  position: absolute;
  bottom: -4px;
  left: 10px;
  z-index: 100;
  width: 5px;
  height: 5px;
  background-color: white;
  border-bottom: 1px solid #ddd;
  border-right: 1px solid #ddd;
  transform: rotate(45deg);
}

/* 删除按钮 */
.teachers-list-item .delete {
  position: absolute;
  left: 515px;
  top: -8px;

}

.teachers-list-item .delete .delete-btn {
  font-size: 18px;
}

/* 添加 */
.add .add-btn button {
  padding: 3px 7px 3px 10px;
}

.search-result {
  text-align: center;
}
</style>
