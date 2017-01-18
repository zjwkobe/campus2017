<template>
    <div id="app">
        <Calendar></Calendar>
        <Airline></Airline>
    </div>
</template>

<script>
    import Calendar from 'components/Calendar';
    import Airline from 'components/Airline';

    export default {
        name: 'app',
        components: {
            Calendar,
            Airline
        }
    }
</script>

<style lang="less">
    @import 'less/reset.less';

    #app {
        width: 912px;
        height: 320px;
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-family: 'Sim Sun';
    }
    .RMB {
        &:before {
            content: '￥';
        }
    }
</style>