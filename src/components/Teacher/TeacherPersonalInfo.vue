

<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">个人信息</div>
    <button @click="test">hhhhh</button>
  </div>
</template>

<script>
export default {
  name: "TeacherPersonalInfo",
  methods: {
    test() {
      this.$axios
        .post(`http://abcs.vaiwan.com/special-workload/upload`, {
          data: [
            {
              achievementName: "数据结构",
              additionThree: "",
              addtionFour: "",
              addtionOne: "",
              addtionTwo: "",
              authorOrder: "第一负责人/第二负责人",
              awardApartment: "",
              awardDate: "",
              awardLevel: "",
              briefIntroduction: "",
              declarantName: "丁建利",
              guidingStudentId: "",
              guidingStudentName: "",
              guidingStudentTeam: "",

              isbn: "",
              level: "国家级/省级/校级",
              projectCategory: "一流本科课程",
              projectName: "",
              projectStatus: "结题",
              publicationName: "",
              publicationsNumber: "",
              receivingHonor: "",
              reportTime: null,
              status: "",
              teacherName: "丁建利",
              teachingScores: "50",
              type: "课程建设（BB1）",
            },
            {
              achievementName: "数据结构",
              additionThree: "",
              addtionFour: "",
              addtionOne: "",
              addtionTwo: "",
              authorOrder: "第一负责人/第二负责人",
              awardApartment: "",
              awardDate: "",
              awardLevel: "",
              briefIntroduction: "",
              declarantName: "丁建利",
              guidingStudentId: "",
              guidingStudentName: "",
              guidingStudentTeam: "",

              isbn: "",
              level: "国家级/省级/校级",
              projectCategory: "一流本科课程",
              projectName: "",
              projectStatus: "结题",
              publicationName: "",
              publicationsNumber: "",
              receivingHonor: "",
              reportTime: null,
              status: "",
              teacherName: "丁建利",
              teachingScores: "50",
              type: "课程建设（BB1）",
            },
          ],
        })
        .then((res) => {
          console.log(res);
        });
    },
  },
};
</script>

<style scoped>
</style>

