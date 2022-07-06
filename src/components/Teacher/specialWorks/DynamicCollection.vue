<template>
  <div class="dynamic">
    <ul>
      <li v-for="(participant, index) in participants" :key="index">
        <label for="name">姓名：</label>
        <input type="text" id="name" v-model="participant.name" />
        <label for="order">排序：</label>
        <input type="number" min="1" id="order" v-model="participant.order" />
        <button class="addBtn" @click="addParticipant" v-if="index == 0">
          
        </button>
        <button
          class="deleteBtn"
          @click="deleteParticipant(index)"
          v-if="index != 0"
        >
          
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "DynamicCollection",
  data() {
    return {
      participants: [
        {
          teacherName: "",
          order: "",
        },
      ],
    };
  },
  methods: {
    //组件刚载入时，或每次添加、删除项目时，都需要将数据同步到父组件中，因为确认提交的控件在父亲组件那
    addParticipant() {
      this.participants.push({
        teacherName: "",
        order: "",
      });
      this.$emit("update", this.participants);
    },
    deleteParticipant(index) {
      this.participants.splice(index, 1);
      this.$emit("update", this.participants);
    },
  },
  created() {
    this.$emit("update", this.participants);
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
button.addBtn {
  color: rgb(84, 160, 87);
}
button.deleteBtn {
  color: rgba(213, 79, 62);
}
.dynamic label {
  font-size: 15px;
  margin-left: 10px;
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