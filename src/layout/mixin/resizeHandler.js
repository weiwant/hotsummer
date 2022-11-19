
const { body } = document
const WIDTH = 992

export default {
    watch: {
        $route() {
            if (this.device === 'mobile') {
                this.$store.dispatch('app/closeSidebar', { withoutAnimation: false })
            }
        }
    },
    beforeMount() {
        window.addEventListener('resize', this.$_resizeHandler)
    },
    beforeDestory() {
        window.removeEventListener('resize', this.$_resizeHandler)
    },
    mounted() {
        if (this.$_isMobile()) {
            this.$store.dispatch('app/toggleDevice', 'mobile');
            this.$store.dispatch('app/closeSidebar', { withoutAnimation: true });
        }
    },
    methods: {
        $_isMobile() {
            const rect = body.getBoundingClientRect();
            return rect.width - 1 < WIDTH
        },
        $_resizeHandler() {
            const isMobile = this.$_isMobile()
            this.$store.dispatch('app/toggleDevice', isMobile ? 'mobile' : 'desktop');
            if (isMobile) {
                this.$store.dispatch('app/closeSidebar', { withoutAnimation: true });
            }
        }
    }
}