<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <member-write-form type="register" />
  </b-container>
</template>

<script>
import MemberWriteForm from "./child/MemberWriteForm.vue";

export default {
  name: "MemberJoin",
  components: {
    MemberWriteForm,
  },
};
</script>

<style></style>
