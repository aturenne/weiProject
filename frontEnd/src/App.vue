<template>
  <div class="app">
    <nav class="navbar">
      <router-link to="/profile/create" class="nav-link">Create Profile</router-link>
      <router-link to="/profile/1" class="nav-link">View Example Profile</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<style>
/* Global styles (not scoped) */
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f8f9fa;
  color: #333;
}

.navbar {
  background: #4d1979;
  padding: 1rem 2rem;
  display: flex;
  gap: 1.5rem;
}

.nav-link {
  color: white;
  text-decoration: none;
  font-weight: 500;
}

.nav-link.router-link-active {
  text-decoration: underline;
  text-underline-offset: 4px;
}
</style>
