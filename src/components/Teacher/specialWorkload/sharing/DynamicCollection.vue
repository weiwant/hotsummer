<template>
  <!-- 
组件内使用的participants的数据来自于父组件
如果父组件为new，那么在最初至少存在两个对象：
1.每个设计多个老师都项目，参与者都至少有1个，也就是项目负责人，由于在所有项目参与者中，项目负责人的order规定为0，故participants默认存在一个order为0的对象
2. 一个空的参与人对象
如果是来自history,那么父组件会拿着id去查数据库，得到participants数组，这就要求，从后台拿到的数组
1. 第一个对象order为0
2. 如果没有其它参与人，前端要自己加一个空对象
 -->
  <div class="dynamic">
    <!-- 项目负责人  -->
    <tr>
      <td>项目负责人</td>
      <td>
        <input
          type="text"
          id="manInCharge"
          placeholder="请输入项目负责人姓名"
          v-model="participants[0].name"
          :disabled="isDisabled"
        />
      </td>
    </tr>
    <!-- 新增参与人 -->
    <tr>
      <td style="vertical-align: middle">参与人</td>
      <!-- v-for一个数字，从1开始， -->
      <td>
        <ul>
          <li v-for="index in participants.length - 1" :key="index">
            <label for="name">姓名</label>
            <input
              type="text"
              id="name"
              v-model="participants[index].teacherName"
              :disabled="isDisabled"
            />
            <label for="order">排序</label>
            <input
              type="number"
              min="1"
              id="order"
              v-model="participants[index].order"
              :disabled="isDisabled"
            />
            <button
              class="addBtn"
              @click="addParticipant"
              v-if="index == 1"
              :disabled="isDisabled"
            >
              
            </button>
            <button
              class="deleteBtn"
              @click="deleteParticipant(index)"
              v-if="index != 1"
              :disabled="isDisabled"
            >
              
            </button>
          </li>
        </ul>
      </td>
    </tr>
  </div>
</template>

<script>
export default {
  name: "DynamicCollection",
  data() {
    return {
      isDisabled: false,
      participants: [
        {
          teacherName: "",
          order: 0,
        },
        {
          teacherName: "",
          order: "",
        },
      ],
    };
  },

  methods: {
    addParticipant() {
      this.participants.push({
        teacherName: "",
        order: "",
      });
    },
    deleteParticipant(index) {
      this.participants.splice(index, 1);
    },
    transmitData() {
      this.$emit("transmit", this.participants);
    },
    changeState() {
      if (this.isDisabled) {
        this.isDisabled = false;
      } else {
        this.isDisabled = true;
      }
    },
  },
};
</script>


<style scoped>
.dynamic {
  padding-top: 10px;
}
ul li {
  margin-top: 10px;
}
button {
  font-family: "icomoon";
  margin-left: 20px;
  border: 0;
  background-color: transparent;
  cursor: pointer;
}
button[class="addBtn"] {
  color: rgb(84, 160, 87);
}
button[class="deleteBtn"] {
  color: rgb(213, 79, 62);
}
.dynamic ul li label[for="name"],
.dynamic ul li label[for="order"] {
  font-size: 15px;
}
input {
  padding-left: 5px;
  width: 100px;
  height: 25px;
  border: 1px solid gray;
  border-radius: 5px;
}
input[type="number"] {
  width: 60px;
}
</style>