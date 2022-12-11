<template>
  <b-container>
    <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group
        id="input-comment-group"
        label="댓글 작성"
        label-for="userid"
        description="댓글"
      >
        <b-form-textarea
          id="content"
          v-model="comments.content"
          type="text"
          placeholder="Enter Comment"
          required
        ></b-form-textarea>
        <b-form-input
          id="writer"
          v-model="comments.writer"
          :disabled="true"
          type="text"
          placeholder="작성자"
          required
        ></b-form-input>
        <b-button type="submit" pill variant="outline-secondary"
          >Submit</b-button
        >
        <b-button type="reset" pill variant="outline-secondary">Reset</b-button>
      </b-form-group>
    </b-form>
    <b-col v-if="memos.length">
      <!-- <tbody> -->
      <memo-list-row
        v-for="(memo, index) in memos"
        :key="index"
        v-bind="memo"
      />
    </b-col>
  </b-container>
</template>

<script>
import { listMemo, writeComment } from "@/api/board";
import MemoListRow from "@/components/board/child/MemoListRow";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  components: {
    MemoListRow,
  },
  computed: {
    ...mapState(memberStore, ["info"]),
  },
  data() {
    return {
      comments: {
        writer: "",
        content: "",
        boardId: this.$route.params.boardId,
      },
      memos: [],
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    console.log(this.$route.params);
    listMemo(
      this.$route.params.boardId,
      (response) => {
        this.memos = response.data;
        console.log(this.memos);
      },
      (error) => {
        console.log("댓글 호출 에러", error);
      }
    );
    this.isUserid = true;
    this.comments.writer = this.info.nickname;
    console.log(this.info);
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_INFO"]),
    onSubmit(event) {
      event.preventDefault();
      this.registerComment();
    },
    registerComment() {
      console.log("submit event comments: " + this.comments.writer);
      writeComment(
        {
          writer: this.comments.writer,
          content: this.comments.content,
          boardId: this.comments.boardId,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
      this.moveComment();
    },
    moveComment() {
      // 현재 페이지 reload할떄 this.$router.go
      this.$router.go({
        name: "BoardView",
        params: { boardId: this.$route.params.boardId },
      });
    },
    onReset(event) {
      event.preventDefault();
      this.comments.content = null;
    },
  },
};
</script>

<style></style>
