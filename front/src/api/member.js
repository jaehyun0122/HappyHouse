import { apiInstance } from "./index.js";

const api = apiInstance();

async function signup(member, success, fail) {
  await api.post(`/member`, JSON.stringify(member)).then(success).catch(fail);
}

async function login(member, success, fail) {
  await api
    .post(`/member/login`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function findById(nickname, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/${nickname}`).then(success).catch(fail);
}

async function modifyMember(nickname, member, success, fail) {
  await api
    .put(`/member/${nickname}`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function deleteMember(nickname, success, fail) {
  await api.delete(`/member/${nickname}`).then(success).catch(fail);
}

// function logout(success, fail)

export { signup, login, findById, modifyMember, deleteMember };
