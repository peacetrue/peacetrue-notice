import {Button, useDataProvider, useNotify, useRefresh} from "react-admin";
import React from "react";
import PublishIcon from '@material-ui/icons/Publish';

export const PublishButton = ({record}) => {
    let dataProvider = useDataProvider();
    let notify = useNotify();
    let refresh = useRefresh();
    return record.stateId === 2 ? null
        : (<Button label={'发布'} onClick={e => {
                e.stopPropagation();
                dataProvider.update('notices/publish', {id: record.id, data: {id: record.id}})
                    .then(data => {
                        notify('发布成功！', 'info', {}, false, null);
                        refresh();
                    });
            }}>
                <PublishIcon/>
            </Button>
        );
}

export default PublishButton;
