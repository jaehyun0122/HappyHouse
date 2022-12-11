<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'BoardView', params: { boardId: boardId } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ writer }}</b-td>
    <b-td>{{ regtime }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    boardId: Number,
    writer: String,
    title: String,
    hit: Number,
    regtime: String,
  },
  created() {
    console.log(this.title);
    console.log(this.regtime);
  },
};
</script>

<style></style>
