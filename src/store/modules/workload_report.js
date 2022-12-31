

const state = {
    //教学工作量确认状态enum
    teachingStatusEnum: {
        NOT_CONFIRMED: 0,
        CONFIRMED: 1,
        NOT_REPLIED: 2,
        REPLIED: 3,
        UNKNOWN: 4
    },

}
const getters = {
    teachingStatusEnumConfig(state) {
        return new Map([
            [
                state.teachingStatusEnum.NOT_CONFIRMED,
                {
                    label: '暂未确认',
                    cssClass: 'not-confirmed',
                }
            ],
            [
                state.teachingStatusEnum.CONFIRMED,
                {
                    label: '已确认',
                    cssClass: 'confirmed',
                }
            ],
            [
                state.teachingStatusEnum.NOT_REPLIED,
                {
                    label: '已反馈，管理员暂未回复',
                    cssClass: 'not-replied',
                }
            ],
            [
                state.teachingStatusEnum.REPLIED,
                {
                    label: '已反馈，管理员已回复',
                    cssClass: 'replied',
                }
            ],
            [
                state.teachingStatusEnum.UNKNOWN,
                {
                    label: '未知',
                    cssClass: 'unknown'
                }
            ]
        ])
    }
}


export default {
    namespaced: true,
    state,
    getters
}