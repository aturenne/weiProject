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
        meta: { title: 'Create Profile', requiresAuth: false, requiresAdmin: false } //will define requiresAuth later in before each
      },
      {
        path: ':id',
        name: 'ViewProfile',
        component: () => import('@/components/ProfileDisplay.vue'),
        meta: { title: 'Profile Details', requiresAuth: true, requiresAdmin: false },
        props: true
      }
    ]
  },
  {
    path: '/profiles',
    name: 'ProfilesList',
    component: () => import('@/views/ProfilesView.vue'),
    meta: { title: 'All Profiles', requiresAuth: true, requiresAdmin: false },
  },
  {
    path: '/availability',
    component: () => import('@/views/AvailabilityView.vue'),
    meta: { title: 'Availability', requiresAuth: true, requiresAdmin: false },
  },
  //Fallback
  {
    path: '/:pathMatch(.*)*',
    redirect: '/profile/create'
  },
  {
  path: '/admin',
  component: () => import('@/views/AdminView.vue'),
  meta: { 
    title: 'Admin Portal',
    requiresAuth: true,
    requiresAdmin: true //for future auth checks (will define later)
  },
  children: [
    //click on a crew member to view their details
    {
      path: 'crew/:userId',
      component: () => import('@/components/CrewDetail.vue'),
      props: (route) => ({ userId: Number(route.params.userId) })
    }
  ]
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