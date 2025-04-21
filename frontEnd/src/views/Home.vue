<template>
  <div class="home-container">
    <h1>Welcome to FrogCrew</h1>
    
    <div v-if="isAuthenticated" class="auth-message">
      <p>You are logged in as: <strong>{{ userRole }}</strong></p>
      <button @click="handleLogout" class="logout-btn">Logout</button>
    </div>
    
    <div v-else class="auth-message">
      <p>Please login to access the scheduling system</p>
      <router-link to="/login" class="login-link">Login</router-link>
    </div>

    <div class="system-info">
      <h2>About the System</h2>
      <p>Manage crew schedules for TCU athletic events including:</p>
      <ul>
        <li>Football</li>
        <li>Basketball</li>
        <li>Baseball</li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { logout, isAuthenticated, getUserRole } from '@/api/auth'
import { ref, watchEffect } from 'vue'

const userRole = ref('')

watchEffect(() => {
  userRole.value = getUserRole()
})

const handleLogout = async () => {
  await logout()
}
</script>

<style scoped>
.home-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
}

.auth-message {
  background: white;
  padding: 1.5rem;
  border-radius: 8px;
  margin: 2rem 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.logout-btn {
  background: #4d1979;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 1rem;
}

.login-link {
  color: #4d1979;
  text-decoration: none;
  font-weight: bold;
}

.system-info {
  background: #f5f5f5;
  padding: 1.5rem;
  border-radius: 8px;
}
</style>