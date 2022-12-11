<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <member-write-form type="modify" />
  </b-container>
</template>

<script>
import MemberWriteForm from "./child/MemberWriteForm.vue";

export default {
  name: "MemberUpdate",
  components: {
    MemberWriteForm,
  },
};
</script>

<style></style>
