<template>
  <!-- <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-td>{{ writer }}</b-td>
    <b-th class="text-left">
      {{ content }}
    </b-th>
    <b-td>{{ regtime }}</b-td>
  </b-tr> -->
  <div>
    <b-list-group>
      <b-list-group-item>작성자 : {{ writer }}</b-list-group-item>
      <b-list-group-item>{{ content }}</b-list-group-item>
    </b-list-group>
    <b-button @click="deleteConfirm">삭제</b-button>
    <br />
  </div>
</template>

<script>
import { deleteComment } from "@/api/board";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemoListRow",
  props: {
    id: Number,
    writer: String,
    content: String,
    regtime: String,
    boardId: Number,
  },
  computed: {
    ...mapState(memberStore, ["info"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_INFO"]),
    deleteConfirm() {
      if (this.writer === this.info.nickname) {
        console.log(this.writer);
        console.log(this.info.nickname);
        if (confirm("정말로 삭제하시겠습니까?")) {
          deleteComment(this.id, () => {
            this.$router.go({ name: "BoardView", params: this.id });
          });
        }
      } else {
        alert("권한이 없습니다.");
      }
    },
  },
};
</script>

<style></style>
