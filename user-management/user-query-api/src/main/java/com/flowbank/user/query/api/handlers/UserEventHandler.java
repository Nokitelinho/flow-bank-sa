package com.flowbank.user.query.api.handlers;

import com.flowbank.user.core.events.UserRegisteredEvent;
import com.flowbank.user.core.events.UserRemovedEvent;
import com.flowbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
