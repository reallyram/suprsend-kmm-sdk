package app.suprsend.android.notification

import kotlinx.serialization.Serializable

@Serializable
data class RawNotification(
    val id: String,
    val apiKey: String,

    // Channel Details
    val channelId: String?,
    val channelName: String?,
    val channelDescription: String?,
    val channelShowBadge: Boolean?,
    val channelVisibility: NotificationChannelVisibility?,
    val channelImportance: NotificationChannelImportance?,

    val priority: NotificationPriority?,

    // Notification Details
    val smallIconDrawableName: String? = null,
    val color: String?,
    val notificationTitle: String?,
    val subText: String?,
    val shortDescription: String?,
    val longDescription: String?,
    val tickerText: String?,
    val iconUrl: String?,
    val imageUrl: String? = null,
    val deeplink: String? = null,

    val category: String? = null,
    val group: String? = null,

    val onGoing: Boolean? = null,
    val autoCancel: Boolean? = null,

    val timeoutAfter: Long? = null,

    val showWhenTimeStamp: Boolean? = null,
    val whenTimeStamp: Long? = null,

    val localOnly: Boolean? = null,

    // Actions
    val actions: List<NotificationActionVo>? = null

) {
    fun getNotificationVo(): NotificationVo {
        var notificationVo = NotificationVo(
            id = id,
            apiKey = apiKey,
            notificationChannelVo = NotificationChannelVo(
                id = channelId ?: "default_channel",
                name = channelName ?: "Default Channel",
                description = channelDescription ?: "",
                showBadge = channelShowBadge ?: true,
                visibility = channelVisibility ?: NotificationChannelVisibility.PUBLIC,
                importance = channelImportance ?: NotificationChannelImportance.HIGH
            ),
            notificationBasicVo = NotificationBasicVo(
                priority = priority ?: NotificationPriority.DEFAULT,
                contentTitle = notificationTitle ?: "",
                contentText = shortDescription ?: "",
                tickerText = tickerText ?: "",
                largeIconUrl = iconUrl,
                color = color,
                subText = subText,
                showWhenTimeStamp = showWhenTimeStamp,
                whenTimeStamp = whenTimeStamp,
                onGoing = onGoing,
                autoCancel = autoCancel,
                smallIconDrawableName = smallIconDrawableName,
                category = category,
                group = group,
                localOnly = localOnly,
                timeoutAfter = timeoutAfter,
                deeplink = deeplink
            ),
            actions = actions
                ?.map { notificationActionVo ->
                    if (notificationActionVo.id == null)
                        notificationActionVo
                            .copy(
                                id = id
                            )
                    else notificationActionVo
                }
        )

        notificationVo = if (!imageUrl.isNullOrBlank()) {
            notificationVo.copy(
                bigPictureVo = BigPictureVo(
                    bigContentTitle = notificationTitle ?: "",
                    summaryText = shortDescription ?: "",
                    bigPictureUrl = imageUrl,
                    largeIconUrl = iconUrl
                )
            )
        } else {
            notificationVo.copy(
                bigTextVo = BigTextVo(
                    title = notificationTitle ?: "",
                    contentText = shortDescription ?: "",
                    bigContentTitle = notificationTitle ?: "",
                    bigText = longDescription ?: ""
                )
            )
        }

        return notificationVo
    }
}

data class NotificationVo(
    val id: String,
    val apiKey: String,
    val notificationChannelVo: NotificationChannelVo,
    val notificationBasicVo: NotificationBasicVo,
    val bigTextVo: BigTextVo? = null,
    val bigPictureVo: BigPictureVo? = null,
    val inboxStyleVo: InBoxStyleVo? = null,
    val actions: List<NotificationActionVo>? = null
) {
    fun getDeeplinkNotificationActionVo(): NotificationActionVo? {
        val deeplink = notificationBasicVo.deeplink
        return if (deeplink == null)
            null
        else
            NotificationActionVo(id = id, apiKey = apiKey, link = deeplink)
    }
}

@Serializable
data class NotificationActionVo(
    val id: String?,
    val apiKey: String?=null,
    val title: String? = null,
    val link: String? = null,
    val iconDrawableName: String? = null
)

data class NotificationChannelVo(
    val id: String,
    val name: String,
    val description: String,
    val showBadge: Boolean,
    val visibility: NotificationChannelVisibility = NotificationChannelVisibility.PUBLIC,
    val importance: NotificationChannelImportance = NotificationChannelImportance.HIGH
)

enum class NotificationChannelVisibility {
    PUBLIC, PRIVATE, SECRET
}

enum class NotificationChannelImportance {
    HIGH, LOW, MAX, MIN, DEFAULT
}

enum class NotificationPriority {
    HIGH, LOW, MAX, MIN, DEFAULT
}

data class NotificationBasicVo(
    val priority: NotificationPriority,
    val smallIconDrawableName: String? = null,
    // #000000
    val color: String? = null,
    val contentTitle: String,
    val subText: String? = null,
    val contentText: String,
    val tickerText: String,
    val largeIconUrl: String? = null,
    val deeplink: String? = null,

    val category: String? = null,
    val group: String? = null,

    val onGoing: Boolean? = null,
    val autoCancel: Boolean? = null,

    val timeoutAfter: Long? = null,

    val showWhenTimeStamp: Boolean? = null,
    val whenTimeStamp: Long? = null,

    val localOnly: Boolean? = null

)

data class BigTextVo(
    val title: String? = null,
    val contentText: String? = null,
    val summaryText: String? = null,
    val bigContentTitle: String? = null,
    val bigText: String? = null
)

data class BigPictureVo(
    val bigContentTitle: String? = null,
    val summaryText: String? = null,
    val bigPictureUrl: String? = null,
    val largeIconUrl: String? = null
)

data class InBoxStyleVo(
    val bigContentTitle: String? = null,
    val summaryText: String? = null,
    val lines: List<String>? = null
)