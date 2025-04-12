// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/profile',
    component: () => import('@/views/ProfileView.vue'),
    children: [
      {
        path: 'create',
        name: 'CreateProfile',
        component: () => import('@/components/ProfileForm.vue'),
        meta: { title: 'Create Profile', requiresAuth: false } //will define requiresAuth later
      },
      {
        path: ':id',
        name: 'ViewProfile',
        component: () => import('@/components/ProfileDisplay.vue'),
        meta: { title: 'Profile Details' },
        props: true
      }
    ]
  },
  {
    path: '/profiles',
    name: 'ProfilesList',
    component: () => import('@/views/ProfilesView.vue'),
    meta: { title: 'All Profiles' }
  },
  {
    path: '/availability',
    component: () => import('@/views/AvailabilityView.vue')
  },
  //Fallback
  {
    path: '/:pathMatch(.*)*',
    redirect: '/profile/create'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to) => {
  document.title = to.meta.title || 'FrogCrew Scheduler';
});

export default router;