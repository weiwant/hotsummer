<template>
    <div id="editable-table">
        <div class="mask" v-show="showLoading">
            <Loading />
        </div>
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <template v-for="header in headerArray">
                            <td v-show="header.show">{{ header.label }}</td>
                        </template>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(data, outerIndex) in dataArray" :key="data.id"
                        :class="{ highlight: outerIndex == currenthighlightRowIndex }"
                        @click="toggleHighlight(outerIndex)">
                        <template v-for="(header, innerIndex) in headerArray">
                            <td v-show="header.show">
                                <div class="editCell" v-if="header.editable">
                                    <!-- 当前cell是编辑状态还是普通状态 -->
                                    <input type="text"
                                        v-if="(currentEditingCellIndex === (outerIndex + 1) * innerIndex)"
                                        @keyup.enter="toggleEdit(outerIndex, innerIndex)" v-model='currentEditingValue'
                                        @click.stop>
                                    <span class="content" v-else @dblclick="toggleEdit(outerIndex, innerIndex)"
                                        @click.stop>
                                        {{
                                                data[header.key]
                                        }}</span>
                                    <!-- 编辑/保存按钮 -->
                                    <button class="edit noBorder green" @click.stop="toggleEdit(outerIndex, innerIndex)"
                                        v-html="currentEditingCellIndex === (outerIndex + 1) * innerIndex ? '' : ''">
                                    </button>
                                </div>
                                <div class="normalCell" v-else> {{ data[header.key] }}</div>
                            </td>
                        </template>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
import Loading from '@/components/Loading.vue'
export default {
    name: "TWEditableTable",
    components: {
        Loading
    },
    props: {
        headerArray: {
            type: Array,
            required: true,
        },
        dataArray: {
            type: Array,
            required: true,
        },
        showLoading: {
            type: Boolean,
            required: true,
        }
    },
    data() {
        return {
            currenthighlightRowIndex: null,
            isEditing: false,  //当前是否有某个单元格正在被编辑
            currentEditingCellIndex: null,  //当前正在被编辑的是第几个cell ( (dataIndex+1) * headerIndex)，dataIndex+1是因为第一行index为0，不论headerIndex是多少都会导致最后结果相同
            currentEditingValue: null, //用于和当前正在被编辑的cell内的input进行双向数据绑定 
            oldValue: null,   //被编辑cell的初始值，只有在current和old不同时，才发送请求
        }
    },
    methods: {
        finishUpdating() {
            this.isEditing = false;
            this.currentEditingCellIndex = null;
            this.currentEditingValue = null;
            this.oldValue = null;
        },
        toggleEdit(outerIndex, innerIndex) {
            let cellIndex = (outerIndex + 1) * innerIndex;
            let key = this.headerArray[innerIndex].key;
            /****如果点击的是正在被编辑的cell的保存按钮****/
            if (this.isEditing && this.currentEditingCellIndex == cellIndex) {
                this.currentEditingValue = this.currentEditingValue.replace(/\s*/g, ""); //去除空格 
                let hasChanged = !(this.currentEditingValue === this.oldValue)
                if (hasChanged) {
                    this.$emit('update', outerIndex, key, this.currentEditingValue)
                } else {
                    this.finishUpdating();
                }
            } else {
                this.isEditing = true;
                this.currentEditingCellIndex = cellIndex;
                this.oldValue = this.currentEditingValue = this.dataArray[outerIndex][key]
            }
        },
        toggleHighlight(outerIndex) {
            if (outerIndex == this.currenthighlightRowIndex) this.currenthighlightRowIndex = null;
            else this.currenthighlightRowIndex = outerIndex;
        }
    },
}
</script>
<style scoped lang="scss">
$cellHeight: 54px;
$hoverBgColor: #f7f7f4;
$highlightBgColor: #f9f5eb;



#editable-table {
    position: relative;
    width: 100%;
}

.mask {
    z-index: 10000;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(187, 187, 187, 0.207);
    backdrop-filter: blur(1px);

    .loading {
        margin-top: 80px;
    }
}

.table-wrapper {
    width: 100%;
    overflow: auto;
    padding-bottom: 50px;
    max-height: 45vh;
}


table {
    border-collapse: collapse;
    white-space: nowrap;
    text-align: center;
}

thead {
    td {
        position: relative;
        padding: 10px 30px;
        font-size: 14px;
        border: 1px solid #eee;
        color: #465757;
        font-weight: 500;
    }

    tr {
        background-color: white;
    }

}

tbody {
    td {
        position: relative;
        padding: 0px 30px;
        font-size: 14px;
        border: 1px solid #eee;
        height: $cellHeight;
        line-height: $cellHeight;
    }

    tr {
        background-color: white;
        transition: all 0.1s;

        &:hover {
            background-color: $hoverBgColor;
        }

        &.highlight {
            background-color: $highlightBgColor;
        }


    }

}

button {
    position: absolute;
    right: 0px;
    top: 50%;
    transform: translateY(-50%);
    font-family: 'icomoon';
}

input {
    border: 1px solid #bbb;
    border-radius: 5px;
    padding: 8px;
}
</style>
