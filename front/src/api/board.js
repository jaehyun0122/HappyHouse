import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(boardId, success, fail) {
  api.get(`/board/${boardId}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(boardId, success, fail) {
  api.delete(`/board/${boardId}`).then(success).catch(fail);
}

// 총 게시글 개수 얻어오는 함수
function totalArticle(success, fail) {
  api.get(`/board/totalCount`).then(success).catch(fail);
}
// 댓글 작성
function writeComment(comments, success, fail) {
  api.post(`/board/memo`, JSON.stringify(comments)).then(success).catch(fail);
  // console.log(
  //   "write comments: " +
  //     comments.userid +
  //     " " +
  //     comments.comment +
  //     " " +
  //     comments.articleno
  // );
}
// 해당 게시글 댓글 불러오기.
function listMemo(boardId, success, fail) {
  api.get(`/board/memo/${boardId}`).then(success).catch(fail);
}

function deleteComment(memono, success, fail) {
  api.delete(`/board/memo/${memono}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  totalArticle,
  writeComment,
  listMemo,
  deleteComment,
};
