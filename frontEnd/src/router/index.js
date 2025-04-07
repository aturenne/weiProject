import { createRouter, createWebHistory } from 'vue-router';

const routes = [
{
    path: '/profile/create',
    name: 'CreateProfile',
    component: () => import('@/components/ProfileForm.vue'), //Lazy-loaded
    meta: { title: 'Create Profile' }
},
{
    path: '/profile/:id',
    name: 'ViewProfile',
    component: () => import('@/components/ProfileDisplay.vue'),
    meta: { title: 'Profile Details' },
    props: true // Pass route params as props
},
{
    path: '/:pathMatch(.*)*', // 404 Catch-all
    redirect: '/profile/create'
}
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

// Update page title
router.beforeEach((to) => {
    document.title = to.meta.title || 'FrogCrew Scheduler';
});

export default router;