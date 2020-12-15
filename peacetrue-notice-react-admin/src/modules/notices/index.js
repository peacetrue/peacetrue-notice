import React from "react";
import {Resource} from "react-admin";
import NotificationsIcon from '@material-ui/icons/Notifications';
import {NoticeList} from './list';
import {NoticeCreate} from './create';
import {NoticeEdit} from './edit';
import {NoticeShow} from './show';

export const Notice = {list: NoticeList, create: NoticeCreate, edit: NoticeEdit, show: NoticeShow};
const NoticeResource = <Resource icon={NotificationsIcon} name="notices" {...Notice} />;
export default NoticeResource;
