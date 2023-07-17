package run.nabla.gallerypicker.picker.result

import androidx.compose.ui.graphics.Color
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
        }
    }
}
