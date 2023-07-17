@file:Suppress("unused")

package run.nabla.gallerypicker.picker.result

import androidx.compose.ui.graphics.Color
import run.nabla.gallerypicker.R
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_BODY_SIZE
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_PRIMARY_ACTION_SIZE
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_SECONDARY_ACTION_SIZE
import run.nabla.gallerypicker.permission.DEFAULT_PERMISSION_TITLE_SIZE
import run.nabla.gallerypicker.picker.DEFAULT_GRID_COLUMNS
import run.nabla.gallerypicker.picker.DEFAULT_HEADER_PADDING_HORIZONTAL
import run.nabla.gallerypicker.picker.DEFAULT_HEADER_PADDING_VERTICAL
import run.nabla.gallerypicker.picker.DEFAULT_HORIZONTAL_PADDING
import run.nabla.gallerypicker.picker.DEFAULT_ITEM_MAX_HEIGHT
import run.nabla.gallerypicker.picker.DEFAULT_ITEM_MIN_HEIGHT
import run.nabla.gallerypicker.picker.DEFAULT_ITEM_ROUNDED_CORNER_SIZE

class GalleryRequest internal constructor() {

    var backgroundColor: Long = Color.Black.value.toLong()
        internal set
    var titleColor: Long = Color.White.value.toLong()
        internal set
    var titlePaddingVertical: Int = DEFAULT_HEADER_PADDING_VERTICAL
        internal set
    var titlePaddingHorizontal: Int = DEFAULT_HEADER_PADDING_HORIZONTAL
        internal set

    var title: String = ""
        internal set

    var titleSize: Int = 20
        internal set

    var showExitAction: Boolean = true
        internal set

    var horizontalPadding: Int = DEFAULT_HORIZONTAL_PADDING
        internal set

    var itemsRoundedCornerSize: Int = DEFAULT_ITEM_ROUNDED_CORNER_SIZE
        internal set
    var gridColumns: Int = DEFAULT_GRID_COLUMNS
        internal set
    var itemMinHeight: Int = DEFAULT_ITEM_MIN_HEIGHT
        internal set
    var itemMaxHeight: Int = DEFAULT_ITEM_MAX_HEIGHT
        internal set

    var permissionTitle: String = ""
        internal set
    var permissionTitleColor: Long = Color.Black.value.toLong()
        internal set
    var permissionTitleSize: Int = DEFAULT_PERMISSION_TITLE_SIZE
        internal set
    var permissionBody: String = ""
        internal set
    var permissionBodySize: Int = DEFAULT_PERMISSION_BODY_SIZE
        internal set
    var permissionBodyColor: Long = Color.Gray.value.toLong()
        internal set
    var permissionBackgroundColor: Long = Color.White.value.toLong()
        internal set
    var permissionImage: Int = R.drawable.ic_permission_unlock
        internal set
    var permissionPrimaryActionTitle: String = ""
        internal set
    var permissionPrimaryActionColor: Long = Color.White.value.toLong()
        internal set
    var permissionPrimaryActionSize: Int = DEFAULT_PERMISSION_PRIMARY_ACTION_SIZE
        internal set
    var permissionPrimaryActionRoundedCorner: Int =
        DEFAULT_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER
        internal set
    var permissionPrimaryActionBackgroundColor: Long =
        Color.Black.value.toLong()
        internal set
    var permissionSecondaryActionTitle: String = ""
        internal set
    var permissionSecondaryActionColor: Long = Color.Black.value.toLong()
        internal set
    var permissionSecondaryActionSize: Int = DEFAULT_PERMISSION_SECONDARY_ACTION_SIZE
        internal set
    var permissionSecondaryActionRoundedCorner: Int =
        DEFAULT_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER
        internal set
    var permissionSecondaryActionBackgroundColor: Long =
        Color.White.value.toLong()
        internal set
    var permissionSecondaryActionBorderColor: Long =
        Color.Black.value.toLong()
        internal set

    /**
     * A builder for constructing [GalleryRequest] instances.
     */
    class Builder {

        private var backgroundColor: Long = Color.Black.value.toLong()

        private var titleColor: Long = Color.White.value.toLong()

        private var titleSize: Int = 20

        private var title: String = ""

        private var showExitAction: Boolean = true

        private var titlePaddingVertical: Int = DEFAULT_HEADER_PADDING_VERTICAL

        private var titlePaddingHorizontal: Int = DEFAULT_HEADER_PADDING_HORIZONTAL

        private var horizontalPadding: Int = DEFAULT_HORIZONTAL_PADDING

        private var itemsRoundedCornerSize: Int = DEFAULT_ITEM_ROUNDED_CORNER_SIZE

        private var gridColumns: Int = DEFAULT_GRID_COLUMNS

        private var itemMinHeight: Int = DEFAULT_ITEM_MIN_HEIGHT

        private var itemMaxHeight: Int = DEFAULT_ITEM_MAX_HEIGHT

        private var permissionTitle: String = ""

        private var permissionTitleColor: Long = Color.Black.value.toLong()

        private var permissionTitleSize: Int = DEFAULT_PERMISSION_TITLE_SIZE

        private var permissionBody: String = ""

        private var permissionBodySize: Int = DEFAULT_PERMISSION_BODY_SIZE

        private var permissionBodyColor: Long = Color.Gray.value.toLong()

        private var permissionBackgroundColor: Long = Color.White.value.toLong()

        private var permissionImage: Int = R.drawable.ic_permission_unlock

        private var permissionPrimaryActionTitle: String = ""

        private var permissionPrimaryActionColor: Long = Color.White.value.toLong()

        private var permissionPrimaryActionSize: Int = DEFAULT_PERMISSION_PRIMARY_ACTION_SIZE

        private var permissionPrimaryActionRoundedCorner: Int =
            DEFAULT_PERMISSION_PRIMARY_ACTION_ROUNDED_CORNER

        private var permissionPrimaryActionBackgroundColor: Long =
            Color.Black.value.toLong()

        private var permissionSecondaryActionTitle: String = ""

        private var permissionSecondaryActionColor: Long = Color.Black.value.toLong()

        private var permissionSecondaryActionSize: Int = DEFAULT_PERMISSION_SECONDARY_ACTION_SIZE

        private var permissionSecondaryActionRoundedCorner: Int =
            DEFAULT_PERMISSION_SECONDARY_ACTION_ROUNDED_CORNER

        private var permissionSecondaryActionBackgroundColor: Long =
            Color.White.value.toLong()

        private var permissionSecondaryActionBorderColor: Long =
            Color.Black.value.toLong()

        /**
         * Set the background color for the screen.
         *
         * @param color the background color to set.
         * @return This builder.
         */
        fun setBackgroundColor(color: Long): Builder {
            this.backgroundColor = color
            return this
        }

        /**
         * Set the title color for the screen.
         *
         * @param color the title color to set.
         * @return This builder.
         */
        fun setTitleColor(color: Long): Builder {
            this.titleColor = color
            return this
        }

        /**
         * Set the title size for the screen.
         *
         * @param size the title size to set.
         * @return This builder.
         */
        fun setTitleSize(size: Int): Builder {
            this.titleSize = size
            return this
        }

        /**
         * Set the title vertical padding for the screen.
         *
         * @param paddingVertical the padding value to set.
         * @return This builder.
         */
        fun setTitlePaddingVertical(paddingVertical: Int): Builder {
            this.titlePaddingVertical = paddingVertical
            return this
        }

        /**
         * Set the title horizontal padding for the screen.
         *
         * @param paddingHorizontal the padding value to set.
         * @return This builder.
         */
        fun setTitlePaddingHorizontal(paddingHorizontal: Int): Builder {
            this.titlePaddingHorizontal = paddingHorizontal
            return this
        }

        /**
         * Set the header title for the [GalleryRequest].
         *
         * @param title header title to go into the GalleryRequest
         * @return This builder.
         */
        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        /**
         * Set whether to show the back button on the screen.
         *
         * @param show true to show the back button, false otherwise.
         * @return This builder.
         */
        fun showExitAction(show: Boolean): Builder {
            this.showExitAction = show
            return this
        }

        /**
         * Set the horizontal padding for the layout in the GalleryRequest.
         *
         * @param padding The horizontal padding value to set.
         * @return This builder.
         */
        fun setHorizontalPadding(padding: Int): Builder {
            this.horizontalPadding = padding
            return this
        }

        /**
         * Set the rounded corner size for the items in the GalleryRequest.
         *
         * @param size The rounded corner size to set.
         * @return This builder.
         */
        fun setItemsRoundedCornerSize(size: Int): Builder {
            this.itemsRoundedCornerSize = size
            return this
        }

        /**
         * Set the number of grid columns for the GalleryRequest.
         *
         * @param columns The number of grid columns to set.
         * @return This builder.
         */
        fun setGridColumns(columns: Int): Builder {
            this.gridColumns = columns
            return this
        }

        /**
         * Set the minimum height for the items in the GalleryRequest.
         *
         * @param height The minimum height value to set.
         * @return This builder.
         */
        fun setItemMinHeight(height: Int): Builder {
            this.itemMinHeight = height
            return this
        }

        /**
         * Set the maximum height for the items in the GalleryRequest.
         *
         * @param height The maximum height value to set.
         * @return This builder.
         */
        fun setItemMaxHeight(height: Int): Builder {
            this.itemMaxHeight = height
            return this
        }

        /**
         * Set the permission title in the GalleryRequest.
         *
         * @param title The permission title to set.
         * @return This builder.
         */
        fun setPermissionTitle(title: String): Builder {
            this.permissionTitle = title
            return this
        }

        /**
         * Set the permission title color in the GalleryRequest.
         *
         * @param color The permission title color to set.
         * @return This builder.
         */
        fun setPermissionTitleColor(color: Long): Builder {
            this.permissionTitleColor = color
            return this
        }

        /**
         * Set the permission title size in the GalleryRequest.
         *
         * @param size The permission title size to set.
         * @return This builder.
         */
        fun setPermissionTitleSize(size: Int): Builder {
            this.permissionTitleSize = size
            return this
        }

        /**
         * Set the permission body in the GalleryRequest.
         *
         * @param body The permission body to set.
         * @return This builder.
         */
        fun setPermissionBody(body: String): Builder {
            this.permissionBody = body
            return this
        }

        /**
         * Set the permission body size in the GalleryRequest.
         *
         * @param size The permission body size to set.
         * @return This builder.
         */
        fun setPermissionBodySize(size: Int): Builder {
            this.permissionBodySize = size
            return this
        }

        /**
         * Set the permission body color in the GalleryRequest.
         *
         * @param color The permission body color to set.
         * @return This builder.
         */
        fun setPermissionBodyColor(color: Long): Builder {
            this.permissionBodyColor = color
            return this
        }

        /**
         * Set the permission background color in the GalleryRequest.
         *
         * @param color The permission background color to set.
         * @return This builder.
         */
        fun setPermissionBackgroundColor(color: Long): Builder {
            this.permissionBackgroundColor = color
            return this
        }

        /**
         * Set the permission image in the GalleryRequest.
         *
         * @param image The permission image to set.
         * @return This builder.
         */
        fun setPermissionImage(image: Int): Builder {
            this.permissionImage = image
            return this
        }

        /**
         * Set the permission primary action title in the GalleryRequest.
         *
         * @param title The permission primary action title to set.
         * @return This builder.
         */
        fun setPermissionPrimaryActionTitle(title: String): Builder {
            this.permissionPrimaryActionTitle = title
            return this
        }

        /**
         * Set the permission primary action color in the GalleryRequest.
         *
         * @param color The permission primary action color to set.
         * @return This builder.
         */
        fun setPermissionPrimaryActionColor(color: Long): Builder {
            this.permissionPrimaryActionColor = color
            return this
        }

        /**
         * Set the permission primary action size in the GalleryRequest.
         *
         * @param size The permission primary action size to set.
         * @return This builder.
         */
        fun setPermissionPrimaryActionSize(size: Int): Builder {
            this.permissionPrimaryActionSize = size
            return this
        }

        /**
         * Set the permission primary action rounded corner in the GalleryRequest.
         *
         * @param roundedCorner The permission primary action rounded corner to set.
         * @return This builder.
         */
        fun setPermissionPrimaryActionRoundedCorner(roundedCorner: Int): Builder {
            this.permissionPrimaryActionRoundedCorner = roundedCorner
            return this
        }

        /**
         * Set the permission primary action background color in the GalleryRequest.
         *
         * @param color The permission primary action background color to set.
         * @return This builder.
         */
        fun setPermissionPrimaryActionBackgroundColor(color: Long): Builder {
            this.permissionPrimaryActionBackgroundColor = color
            return this
        }

        /**
         * Set the permission secondary action title in the GalleryRequest.
         *
         * @param title The permission secondary action title to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionTitle(title: String): Builder {
            this.permissionSecondaryActionTitle = title
            return this
        }

        /**
         * Set the permission secondary action color in the GalleryRequest.
         *
         * @param color The permission secondary action color to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionColor(color: Long): Builder {
            this.permissionSecondaryActionColor = color
            return this
        }

        /**
         * Set the permission secondary action size in the GalleryRequest.
         *
         * @param size The permission secondary action size to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionSize(size: Int): Builder {
            this.permissionSecondaryActionSize = size
            return this
        }

        /**
         * Set the permission secondary action rounded corner in the GalleryRequest.
         *
         * @param roundedCorner The permission secondary action rounded corner to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionRoundedCorner(roundedCorner: Int): Builder {
            this.permissionSecondaryActionRoundedCorner = roundedCorner
            return this
        }

        /**
         * Set the permission secondary action background color in the GalleryRequest.
         *
         * @param color The permission secondary action background color to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionBackgroundColor(color: Long): Builder {
            this.permissionSecondaryActionBackgroundColor = color
            return this
        }

        /**
         * Set the permission secondary action border color in the GalleryRequest.
         *
         * @param color The permission secondary action border color to set.
         * @return This builder.
         */
        fun setPermissionSecondaryActionBorderColor(color: Long): Builder {
            this.permissionSecondaryActionBorderColor = color
            return this
        }

        /**
         * Build the GalleryRequest specified by this builder.
         *
         * @return the newly constructed GalleryRequest.
         */
        fun build(): GalleryRequest = GalleryRequest().apply {
            this.backgroundColor = this@Builder.backgroundColor
            this.titleColor = this@Builder.titleColor
            this.title = this@Builder.title
            this.titleSize = this@Builder.titleSize
            this.showExitAction = this@Builder.showExitAction
            this.titlePaddingVertical = this@Builder.titlePaddingVertical
            this.titlePaddingHorizontal = this@Builder.titlePaddingHorizontal
            this.horizontalPadding = this@Builder.horizontalPadding
            this.itemsRoundedCornerSize = this@Builder.itemsRoundedCornerSize
            this.gridColumns = this@Builder.gridColumns
            this.itemMinHeight = this@Builder.itemMinHeight
            this.itemMaxHeight = this@Builder.itemMaxHeight

            this.permissionTitle = this@Builder.permissionTitle
            this.permissionTitleColor = this@Builder.permissionTitleColor
            this.permissionTitleSize = this@Builder.permissionTitleSize
            this.permissionBody = this@Builder.permissionBody
            this.permissionBodySize = this@Builder.permissionBodySize
            this.permissionBodyColor = this@Builder.permissionBodyColor
            this.permissionBackgroundColor = this@Builder.permissionBackgroundColor
            this.permissionImage = this@Builder.permissionImage
            this.permissionPrimaryActionTitle = this@Builder.permissionPrimaryActionTitle
            this.permissionPrimaryActionColor = this@Builder.permissionPrimaryActionColor
            this.permissionPrimaryActionSize = this@Builder.permissionPrimaryActionSize
            this.permissionPrimaryActionRoundedCorner =
                this@Builder.permissionPrimaryActionRoundedCorner
            this.permissionPrimaryActionBackgroundColor =
                this@Builder.permissionPrimaryActionBackgroundColor
            this.permissionSecondaryActionTitle = this@Builder.permissionSecondaryActionTitle
            this.permissionSecondaryActionColor = this@Builder.permissionSecondaryActionColor
            this.permissionSecondaryActionSize = this@Builder.permissionSecondaryActionSize
            this.permissionSecondaryActionRoundedCorner =
                this@Builder.permissionSecondaryActionRoundedCorner
            this.permissionSecondaryActionBackgroundColor =
                this@Builder.permissionSecondaryActionBackgroundColor
            this.permissionSecondaryActionBorderColor =
                this@Builder.permissionSecondaryActionBorderColor
        }
    }
}
