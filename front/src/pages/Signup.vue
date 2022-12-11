<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-10">
          <edit-profile-form type="register">
          </edit-profile-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import EditProfileForm from './UserProfile/EditProfileForm.vue'

  export default {
    components: {
      EditProfileForm
    }
  }

</script>
<style>

</style>
