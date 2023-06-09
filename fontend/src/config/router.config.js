import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: "/",
        redirect: "/LoginPage",
    },
    {
        path: "/LoginPage",
        component: () => import("@/layout/LoginPage.vue"),
    },
    {
        path: "/DashboardPage",
        component: () => import("@/layout/DashboardPage"),
    },
];

const router = createRouter({
    history: createWebHistory("/"),
    routes,
});

export default router;