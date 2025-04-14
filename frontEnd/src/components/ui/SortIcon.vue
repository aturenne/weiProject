<template>
    <span class="sort-icon">
        <svg v-if="direction === 'asc'" width="12" height="12" viewBox="0 0 24 24">
            <path d="M12 3l-8 8h16z" />
        </svg>
        <svg v-else-if="direction === 'desc'" width="12" height="12" viewBox="0 0 24 24">
            <path d="M12 21l8-8H4z" />
        </svg>
        <svg v-else width="12" height="12" viewBox="0 0 24 24">
            <path d="M12 3l-8 8h16zm0 18l8-8H4z" />
        </svg>
    </span>
</template>

<script setup>
defineProps({
    direction: {
        type: String,
        validator: value => [null, 'asc', 'desc'].includes(value)
    }
});
</script>

<style scoped>
.sort-icon {
    display: inline-block;
    margin-left: 4px;
}

.sort-icon svg {
    width: 12px;
    height: 12px;
    fill: currentColor;
    vertical-align: middle;
}
</style>