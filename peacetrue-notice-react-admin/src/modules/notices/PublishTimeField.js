import {DateField, Labeled} from "react-admin";
import React from "react";

export const PublishTimeField = ({label = '发布时间', record, withLabel = false, ...rest}) =>
    record.stateId === 1
        ? null
        : withLabel ?
        (<Labeled label={label}>
            <DateField source="publishedTime" record={record} {...rest} showTime/>
        </Labeled>)
        : <DateField source="publishedTime" record={record} {...rest} showTime/>;

export default PublishTimeField;
