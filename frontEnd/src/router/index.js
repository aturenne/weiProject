import { createRouter, createWebHistory } from 'vue-router';

import { getUserRole, isAuthenticated } from '@/api/auth.js'

const routes = [
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Home.vue'),
    meta: { title: 'Home', requiresAuth: false }
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login.vue'),
    meta: { title: 'Login', requiresAuth: false }
  },
  {
    path: '/profile',
    component: () => import('@/views/ProfileView.vue'),
    children: [
      {
        path: 'create',
        name: 'CreateProfile',
        component: () => import('@/components/ProfileForm.vue'),
        meta: { title: 'Create Profile', requiresAuth: true } //will define requiresAuth later in before each
      },
      {
        path: ':id',
        name: 'ViewProfile',
        component: () => import('@/components/ProfileDisplay.vue'),
        meta: { title: 'Profile Details', requiresAuth: true, roles: ['crew', 'admin'] },
        props: true
      }
    ]
  },
  {
    path: '/profiles',
    name: 'ProfilesList',
    component: () => import('@/views/ProfilesView.vue'),
    meta: { title: 'All Profiles', requiresAuth: true, roles: ['crew', 'admin'] },
  },
  {
    path: '/availability',
    component: () => import('@/views/AvailabilityView.vue'),
    meta: { title: 'Availability', requiresAuth: true, roles: ['crew', 'admin'] },
  },
  {
  path: '/admin',
  component: () => import('@/views/AdminView.vue'),
  meta: { 
    title: 'Admin Portal',
    requiresAuth: true,
    roles: ['admin']
  },
  children: [
    //click on a crew member to view their details
    {
      path: 'crew/:userId',
      component: () => import('@/components/CrewDetail.vue'),
      props: (route) => ({ userId: Number(route.params.userId) })
    }
  ]
},
{
  path: '/scheduling',
  component: () => import('@/views/SchedulingView.vue'),
  meta: { 
    title: 'Scheduling',
    requiresAuth: true,
    roles: ['admin']
  }
},
//Fallback
  {
    path: '/:pathMatch(.*)*',
    redirect: '/home'
  },
];

const router = createRouter({
  history: createWebHistory(),
  redirect: '/home',
  routes
});

router.beforeEach((to, from) => {
  document.title = to.meta.title || 'FrogCrew Scheduler';
  console.log(from.name, '->', to.name)
  if (to.meta.requiresAuth && !isAuthenticated.value) {
    // Redirect to the login page with the originally requested page as the redirect query parameter
    return { name: 'login', query: { redirect: to.fullPath } }
  }

  const userRole = getUserRole()
  // Check role-based access
  if (to.meta.roles && !to.meta.roles.includes(userRole)) {
    alert('You do not have permission to access this page.')
    return { name: 'home' } // Redirect to the home page
  }
});

export default router;