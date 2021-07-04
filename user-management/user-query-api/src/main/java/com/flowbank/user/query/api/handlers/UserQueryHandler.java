package com.flowbank.user.query.api.handlers;

import com.flowbank.user.query.api.dto.UserLookupResponse;
import com.flowbank.user.query.api.queries.FindAllUsersQuery;
import com.flowbank.user.query.api.queries.FindUserByIdQuery;
import com.flowbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);

    UserLookupResponse searchUsers(SearchUsersQuery query);

    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
